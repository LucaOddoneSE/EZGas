package it.polito.ezgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

@SpringBootApplication
public class BootEZGasApplication {
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BootEZGasApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData() throws SQLException{
		
		Iterator<User> iter;
		
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		conn.close();
		
		
		/*
		 
		list all the users stored in the database and, if there is no an admin user create it
		and then save it in the db
	
		*/
		
		System.out.println("All users stored in the database:\n");
		if(userRepository.findAll().size() != 0) {
			iter = userRepository.findAll().iterator();
			while(iter.hasNext()) {
				if (iter.next().getAdmin() == true)
					return;
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
       return ;
	}

}
