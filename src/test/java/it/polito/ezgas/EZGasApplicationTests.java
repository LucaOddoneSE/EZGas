package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.*;
import it.polito.ezgas.entity.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {
	
// GasStation Entity
	
	@Test
    public void testGasStationId() {
		GasStation GasStation = new GasStation();
		int GasStationId = 1;
		GasStation.setGasStationId(GasStationId);
		assert(GasStation.getGasStationId() == GasStationId);
    }

		
	@Test
    public void testGasStationName() {
		GasStation GasStation = new GasStation();
		String GasStationName = "test";
		GasStation.setGasStationName(GasStationName);
		assert(GasStation.getGasStationName() == GasStationName);
    }
	
	
	@Test
    public void testGasStationAddress() {
		GasStation GasStation = new GasStation();
		String GasStationAddress = "Politecnico di Torino Turin Piedmonte Italy";
		GasStation.setGasStationAddress(GasStationAddress);
		assert(GasStation.getGasStationAddress() == GasStationAddress);
    }


	@Test
    public void testReportDependability() {
		GasStation GasStation = new GasStation();
		Double ReportDependability = 1.2 ;
		GasStation.setReportDependability(ReportDependability);
		assert(GasStation.getReportDependability() == ReportDependability);
    }
	
	
	// Price Report Entity
	
	
	@Test
    public void testDieselPrice() {
		PriceReport PriceReport = new PriceReport(null, 0, 0, 0, 0);
		Double DieselPrice = 23.2;
		PriceReport.setDieselPrice(DieselPrice);
		assert(PriceReport.getDieselPrice() == DieselPrice);
    }

	@Test
    public void testPriceReportId() {
		PriceReport PriceReport = new PriceReport(null, 0, 0, 0, 0);
		int PriceReportId = 100;
		PriceReport.setPriceReportId(PriceReportId);
		assert(PriceReport.getPriceReportId() == PriceReportId);
    }

	// User Entity 
	@Test
    public void testUserId() {
		User user = new User();
		int UserId = 20;
		user.setUserId(UserId);
		assert(user.getUserId() == UserId);
    }
	
	@Test
    public void testUserName() {
		User user = new User();
		String UserName = "test";
		user.setUserName(UserName);
		assert(user.getUserName() == UserName);
    }
	
	@Test
    public void testPassword() {
		User user = new User();
		String Pass = "test!@";
		user.setPassword(Pass);
		assert(user.getUserName() == Pass);
    }

	
	// Login dto
	
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
		
	
	
	// IdPw dto

	@Test
    public void TestIdPwUser() {
		IdPw IdPass = new IdPw();
		String user = "Test";
		IdPass.setUser(user);
		assert(IdPass.getUser()==user);
    }
	
	@Test
    public void TestIdPwPass() {
		IdPw IdPass = new IdPw();
		String pass = "Test";
		IdPass.setPw(pass);
		assert(IdPass.getPw()== pass);
    }
	
	// Price Report Dto
	@Test
    public void TestDiselPrice() {
		PriceReportDto PriceReport = new PriceReportDto(null, null, 0, 0, 0, 0);
		double DiselPrice = 83.2;
		PriceReport.setDieselPrice(DiselPrice);
		assert(PriceReport.getDieselPrice() == DiselPrice);
    }
	
	@Test
    public void TestPriceReportId() {
		PriceReportDto PriceReport = new PriceReportDto(null, null, 0, 0, 0, 0);
		Integer PriceReportId = 12;
		PriceReport.setPriceReportId(PriceReportId);
		assert(PriceReport.getPriceReportId() == PriceReportId);
    }
	

}




