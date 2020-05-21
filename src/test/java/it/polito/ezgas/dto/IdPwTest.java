package it.polito.ezgas.dto;

import org.junit.jupiter.api.Test;

class IdPwTest {

	@Test
    public void TestIdPwUser() {
		IdPw IdPass = new IdPw();
		String user = "Test";
		IdPass.setUser(user);
		assert(IdPass.getUser()==user);
    }
	
	@Test
    public void TestIdPwUser1() {
		IdPw IdPass = new IdPw();
		String user = null;
		IdPass.setUser(user);
		assert(IdPass.getUser()==user);
    }
	
	@Test
    public void TestIdPwUser2() {
		IdPw IdPass = new IdPw();
		String user = "";
		IdPass.setUser(user);
		assert(IdPass.getUser()==user);
    }
	
	
	@Test
    public void TestIdPwPass() {
		IdPw IdPass = new IdPw();
		String pass = "TestPass";
		IdPass.setPw(pass);
		assert(IdPass.getPw()== pass);
    }
	
	@Test
    public void TestIdPwPass1() {
		IdPw IdPass = new IdPw();
		String pass = "";
		IdPass.setPw(pass);
		assert(IdPass.getPw()== pass);
    }
	
	@Test
    public void TestIdPwPass2() {
		IdPw IdPass = new IdPw();
		String pass = null;
		IdPass.setPw(pass);
		assert(IdPass.getPw()== pass);
    }

}
