package org.deshand;

import org.deshand.model.Role;
import org.deshand.model.User;
import org.deshand.service.RoleRepository;
import org.deshand.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("unused")
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(new Object[] { Application.class }, args);
	}

	// Check findByAuthorities method

//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private RoleRepository roleRepository;

	// @Bean
	// public CommandLineRunner readExcelData(CentralWareHouseRepository repository,
	// ApachePOIExcelRead reader) {
	// return (args) -> {
	// // repository.deleteAll();
	// reader.processExcelFile();
	// };
	// }
//
//	@Bean
//	public CommandLineRunner createUsers(UserRepository userRepository, RoleRepository roleRepository) {
//		return (args) -> {
//
//			Role adminRole;
//			Role userRole;
//			roleRepository.deleteAll();
//			System.out.println(roleRepository.findByAuthority("ADMIN"));
//			if (roleRepository.count() == 0) {
//				adminRole = new Role("ROLE_ADMIN");
//				roleRepository.save(adminRole);
//				System.out.println(roleRepository.findByAuthority("ADMIN"));
//				userRole = new Role("ROLE_USER");
//				roleRepository.save(userRole);
//			} else {
//				adminRole = roleRepository.findByAuthority("ADMIN");
//				userRole = roleRepository.findByAuthority("USER");
//			}
//			userRepository.deleteAll();
//			if (userRepository.count() == 0) {
//				userRepository.save(createUser("admin", "DlaHrod13$", "Administrator", adminRole, userRole));
//				userRepository.save(createUser("user", "user", "John Doe", userRole));
//				userRepository.save(createUser("a_lipin", "2810", "Алексей Липин", userRole));
//				userRepository.save(createUser("a_deshko", "6864", "Андрей Дешко", adminRole, userRole));
//			}
//
//		};
//	}
//
//	private User createUser(String username, String password, String fullname, Role... roles) {
//		User user = new User();
//		user.setUsername(username);
//		user.setUnencryptedPassword(password);
//		user.setFullName(fullname);
//		for (Role role : roles) {
//			user.addAuthority(role);
//		}
//		return user;
//	}
//
}

// https://www.youtube.com/watch?v=qUBt8k4pQgQ&feature=youtu.be&t=581
// https://ralf.schaeftlein.com/2015/03/26/using-vaadin-ui-with-spring-boot-for-spring-data-backend-based-on-mongodb/
// https://github.com/marcushellberg/spring-boot-todo
// https://github.com/vaadin/spring
// comment