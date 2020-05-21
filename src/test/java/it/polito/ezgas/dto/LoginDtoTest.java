package it.polito.ezgas.dto;

import org.junit.jupiter.api.Test;

public class LoginDtoTest {


	@Test
    public void testUserName() {
		LoginDto login = new LoginDto();
		String UserName = "Fereshteh Feizabadi";
		login.setUserName(UserName);
		assert(login.getUserName() == UserName);
    }
	@Test
    public void testUserName1() {
		LoginDto login = new LoginDto();
		String UserName = "";
		login.setUserName(UserName);
		assert(login.getUserName() == UserName);
    }
	
	
	@Test
    public void testUserName2() {
		LoginDto login = new LoginDto();
		String UserName = null;
		login.setUserName(UserName);
		assert(login.getUserName() == UserName);
    }
	
	
	@Test
    public void testAdmin() {
		LoginDto login = new LoginDto();
		boolean admin = false;
		login.setAdmin(admin);
		assert(login.getAdmin() == admin);
    }
	
	@Test
    public void testAdmin1() {
		LoginDto login = new LoginDto();
		boolean admin = true;
		login.setAdmin(admin);
		assert(login.getAdmin() == admin);
    }
	
}
