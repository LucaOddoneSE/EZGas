package it.polito.ezgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.polito.ezgas.controller.UserController;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

@SpringBootApplication
public class BootEZGasApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private UserController userController;
	
	public static void main(String[] args) {
		SpringApplication.run(BootEZGasApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData() throws SQLException{
		
		List<UserDto> listUsers = new ArrayList<>();
		Iterator<UserDto> iter;
		
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		conn.close();
		
		
		/*
		 
		list all the users stored in the database and, if there is no an admin user create it
		and then save it in the db
	
		*/
		
		System.out.println("All users stored in the database:\n");
		if(userController.getAllUsers().size() != 0) {
			System.out.println(userController.getAllUsers().toString());
			listUsers = userController.getAllUsers();
			iter = listUsers.iterator();
			while(iter.hasNext()) {
				if (iter.next().getAdmin() == true)
					break;
			}
			User user= new User("admin", "admin", "admin@ezgas.com", 5);
		    user.setAdmin(true);
		    userRepository.save(user);
		}
		else {
			System.out.println("None\n");
			User user= new User("admin", "admin", "admin@ezgas.com", 5);
		    user.setAdmin(true);
		    userRepository.save(user);
		}

	}

}
