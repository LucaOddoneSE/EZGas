package it.polito.ezgas;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.*;
import it.polito.ezgas.entity.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {
	
// GasStation
	
	@Test
    public void testGasStationId() {
		GasStation GasStation = new GasStation();
		int GasStationId = 1;
		GasStation.setGasStationId(GasStationId);
		assert(GasStation.getGasStationId() == GasStationId);
    }

	@Test
    public void testGasStationId1() {
		GasStation GasStation = new GasStation();
		int GasStationId = -1;
		GasStation.setGasStationId(GasStationId);
		assert(GasStation.getGasStationId() == GasStationId);
    }
	

	
		
	@Test
    public void testGasStationName() {
		GasStation GasStation = new GasStation();
		String GasStationName = "GiacomoBalla";
		GasStation.setGasStationName(GasStationName);
		assert(GasStation.getGasStationName() == GasStationName);
    }
	
	@Test
    public void testGasStationName1() {
		GasStation GasStation = new GasStation();
		String GasStationName = "";
		GasStation.setGasStationName(GasStationName);
		assert(GasStation.getGasStationName() == GasStationName);
    }
	
	
	

	@Test
    public void testReportDependability() {
		GasStation GasStation = new GasStation();
		Double ReportDependability = Double.MAX_VALUE+1;
		GasStation.setReportDependability(ReportDependability);
		assert(GasStation.getReportDependability() == ReportDependability);
    }
	
	@Test
    public void testReportDependability1() {
		GasStation GasStation = new GasStation();
		Double ReportDependability = 4.2;
		GasStation.setReportDependability(ReportDependability);
		assert(GasStation.getReportDependability() == ReportDependability);
    }
	
	
	@Test
    public void testReportDependability2() {
		GasStation GasStation = new GasStation();
		Double ReportDependability = -4.2;
		GasStation.setReportDependability(ReportDependability);
		assert(GasStation.getReportDependability() == ReportDependability);
    }
	
	
	
	// User 
	@Test
    public void testUserId() {
		User user = new User();
		int UserId = Integer.MAX_VALUE+1;
		user.setUserId(UserId);
		assert(user.getUserId() == UserId);
    }
	
    public void testUserId1() {
		User user = new User();
		int UserId = 4;
		user.setUserId(UserId);
		assert(user.getUserId() == UserId);
    }
    
    public void testUserId2() {
		User user = new User();
		int UserId = -4;
		user.setUserId(UserId);
		assert(user.getUserId() == UserId);
    }
    
    
	@Test
    public void testPassword() {
		User user = new User();
		String Pass = "test!@";
		user.setPassword(Pass);
		assert(user.getPassword() == Pass);
    }
    
    
	@Test
    public void testEmail() {
		User user = new User();
		String Email = "s274475@studenti.polito.it";
		user.setEmail(Email);
		assert(user.getEmail() == Email);
    }
	
	@Test
    public void testReputation1() {
		User user = new User();
		Integer reputation = 5+1;
		user.setReputation(reputation);
		assert(user.getReputation() == reputation);
    }
	
	@Test
    public void testReputation2() {
		User user = new User();
		Integer reputation = -5-1;
		user.setReputation(reputation);
		assert(user.getReputation() == reputation);
    }
	
	@Test
    public void testReputation3() {
		User user = new User();
		Integer reputation = 1;
		user.setReputation(reputation);
		assert(user.getReputation() == reputation);
    }
	
	@Test
    public void testReputation4() {
		User user = new User();
		Integer reputation = -1;
		user.setReputation(reputation);
		assert(user.getReputation() == reputation);
    }

	
	// Login 
	
		
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
		
	
	
	
	// IdPw 

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




