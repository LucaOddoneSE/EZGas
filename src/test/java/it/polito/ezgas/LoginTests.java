package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.LoginDto;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTests {

	@Test
    public void testAdmin() {
		LoginDto login = new LoginDto();
		boolean admin = true;
		login.setAdmin(admin);
		assert(login.getAdmin() == admin);
    }
	
	
	@Test
    public void testReputation() {
		LoginDto login = new LoginDto();
		Integer Reputation = 12;
		login.setReputation(Reputation);
		assert(login.getReputation() == Reputation);
    }

	@Test
    public void testEmail() {
		LoginDto login = new LoginDto();
		String Email = "s274475@polito.it";
		login.setEmail(Email);
		assert(login.getEmail()==Email);
    }

	
	@Test
	public void contextLoads() {
	}
	

}
