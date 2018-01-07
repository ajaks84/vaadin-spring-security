package org.deshand.editor;

import org.deshand.model.CentralWareHouse;
import org.deshand.repo.CentralWareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
@SuppressWarnings("deprecation")
public class CentralWareHouseEditor extends VerticalLayout{

	private static final long serialVersionUID = -696429844354467905L;

	private final CentralWareHouseRepository repository;
	
	private CentralWareHouse centralWareHouse;
	
	/* Fields to edit properties in CentralWareHouse entity */
	TextField shelfName = new TextField("Shelf Name");
	TextField hasValueMetal = new TextField("Value Metal");
	TextField partDescription = new TextField("Part Description");
	TextField partNumber = new TextField("Part Number");
	TextField wHNumber = new TextField("Warehouse Number");
	TextField quantity = new TextField("Quantity");
	TextField bKQuantity = new TextField("Bookkeeper's Quantity");
	TextField missingQuantity = new TextField("Missing Quantity");
	TextField placeOfInstallation = new TextField("Place of installation");

	/* Action buttons */
	Button save = new Button("Save", FontAwesome.SAVE);
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete);

	Binder<CentralWareHouse> binder = new Binder<>(CentralWareHouse.class);
	
	@Autowired
	public CentralWareHouseEditor(CentralWareHouseRepository repository) {
		this.repository = repository;
		HorizontalLayout editorSpace = new HorizontalLayout(shelfName, hasValueMetal, partDescription, partNumber, 
			      wHNumber, quantity, bKQuantity, missingQuantity, placeOfInstallation);

		addComponents(editorSpace, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> repository.save(centralWareHouse));
		delete.addClickListener(e -> repository.delete(centralWareHouse));
		cancel.addClickListener(e -> editCentralWareHouse(centralWareHouse));
		setVisible(false);
	}
	
	public interface ChangeHandler {
		void onChange();
	}
	
	public final void editCentralWareHouse(CentralWareHouse cWH) {
		if (cWH == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = cWH.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			centralWareHouse = repository.findById(cWH.getId());
		}
		else {
			centralWareHouse = cWH;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(centralWareHouse);

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		partDescription.selectAll();
	}
	
	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}
