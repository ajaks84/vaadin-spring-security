package org.deshand.ui.views;

import javax.annotation.PostConstruct;

import org.deshand.editor.CentralWareHouseEditor;
import org.deshand.model.CentralWareHouse;
import org.deshand.repo.CentralWareHouseRepository;
import org.deshand.ui.components.GoToMainViewLink;
import org.deshand.ui.components.LogoutLink;
import org.deshand.ui.events.UserLoggedInEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;

import com.google.common.eventbus.Subscribe;
//import com.vaadin.data.util.ObjectProperty;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ValueChangeMode;
//import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("deprecation")
@Secured("ROLE_USER")
@SpringView(name = ProfileView.NAME)
public class ProfileView extends AbstractView {

	private static final long serialVersionUID = 229023845374523161L;

	private Logger LOG = LoggerFactory.getLogger(ProfileView.class);

	public final static String NAME = "profile";

	private final CentralWareHouseRepository repo;

	@SuppressWarnings("unused")
	private final CentralWareHouseEditor editor;

	public final Grid<CentralWareHouse> grid;

	final TextField filterByDescription;

	final TextField filterByShelf;

	final TextField filterByPartNumber;

	private final Button addNewBtn;

	private LogoutLink logoutLink;

	public ProfileView(CentralWareHouseRepository repo, CentralWareHouseEditor editor) {
		this.repo = repo;
		this.editor = editor;
		this.grid = new Grid<>(CentralWareHouse.class);
		this.filterByDescription = new TextField();
		this.filterByShelf = new TextField();
		this.filterByPartNumber = new TextField();
		this.addNewBtn = new Button("Новая запись в таблицу", FontAwesome.PLUS);
		this.logoutLink = new LogoutLink();
		
		this.logoutLink.updateVisibility();

		registerWithEventbus();

		// build layout
		HorizontalLayout navigator = new HorizontalLayout(new GoToMainViewLink(),logoutLink);
		HorizontalLayout actions = new HorizontalLayout(filterByShelf, filterByDescription, filterByPartNumber,
				addNewBtn);
		HorizontalLayout editorSpace = new HorizontalLayout(editor);
		VerticalLayout mainLayout = new VerticalLayout(navigator,actions, grid, editorSpace);
		setContent(mainLayout);

		grid.setHeight(600, Unit.PIXELS);
		grid.setWidth(1870, Unit.PIXELS);
		// grid.setHeight(300, Unit.PIXELS);
		// grid.setWidth(970, Unit.PIXELS); //, "hasValueMetal"
		grid.setColumns("shelfName", "partDescription", "partNumber", "wHNumber", "quantity", "bKQuantity",
				"missingQuantity", "placeOfInstallation");

		filterByShelf.setPlaceholder("Номер Полки");
		filterByDescription.setPlaceholder("Описание");
		filterByPartNumber.setPlaceholder("Номер Заказа");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filterByShelf.setValueChangeMode(ValueChangeMode.LAZY);
		filterByShelf.addValueChangeListener(e -> listEntries(e.getValue(), "shelf"));

		filterByDescription.setValueChangeMode(ValueChangeMode.LAZY);
		filterByDescription.addValueChangeListener(e -> listEntries(e.getValue(), "description"));

		filterByPartNumber.setValueChangeMode(ValueChangeMode.LAZY);
		filterByPartNumber.addValueChangeListener(e -> listEntries(e.getValue(), "partNumber"));

		// Connect selected Customer to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editCentralWareHouse(e.getValue());
		});

		// Instantiate and edit new Customer the new button is clicked
		addNewBtn.addClickListener(
				e -> editor.editCentralWareHouse(new CentralWareHouse("", "", "", "", "", "", "", "", "")));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listEntries(filterByDescription.getValue(), "description");
		});

		// Initialize listing
		listEntries(null, null);
	}

	@PostConstruct
	public void postConstruct() {
		LOG.info("Created new instance of ProfileView");
	}

	@Override
	public void enter(ViewChangeEvent event) {
		LOG.info("Entering profile view");
	}

	@Subscribe
	public void userLoggedIn(UserLoggedInEvent event) {
		logoutLink.updateVisibility();
	}

	public void listEntries(String filterText, String option) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repo.findBy("Failed", new PageRequest(0, 100)));
		} else if (option == "shelf") {
			filterByPartNumber.clear();
			filterByDescription.clear();
			grid.setItems(repo.findByshelfNameLikeIgnoreCase(filterText));
		} else if (option == "description") {
			filterByPartNumber.clear();
			filterByShelf.clear();
			grid.setItems(repo.findBypartDescriptionLikeIgnoreCase(filterText));
		} else if (option == "partNumber") {
			filterByDescription.clear();
			filterByShelf.clear();
			grid.setItems(repo.findBypartNumberStartsWithIgnoreCase(filterText));

		}
	}
}
