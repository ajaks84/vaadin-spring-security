package org.deshand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(new Object[] { Application.class }, args);
	}

	// @Bean
	// public CommandLineRunner readExcelData(CentralWareHouseRepository repository,
	// ApachePOIExcelRead reader) {
	// return (args) -> {
	//// repository.deleteAll();
	//// reader.readExcel2();
	//
	// };
	// }

}

// https://www.youtube.com/watch?v=qUBt8k4pQgQ&feature=youtu.be&t=581
// https://ralf.schaeftlein.com/2015/03/26/using-vaadin-ui-with-spring-boot-for-spring-data-backend-based-on-mongodb/
// https://github.com/marcushellberg/spring-boot-todo
// https://github.com/vaadin/spring
// comment