package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.*;
import it.polito.ezgas.entity.*;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.impl.GasStationServiceimpl;
import exception.InvalidGasTypeException;
import it.polito.ezgas.dto.UserDto;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {
	
	private static final Integer id = 123;
	private static final String name = "Repsol";
	private static final String address = "Via Roma";
	private static final boolean diesel = true;
	private static final boolean super1 = true;
	private static final boolean superPlus = true;
	private static final boolean gas = true;
	private static final boolean methane = true;
	private static final String carSharing = "Blablacar";
	private static final double lat = 45;
	private static final double lon = 120;
	private static final double dPrice = 1.3;
	private static final double sPrice = 0.9;
	private static final double spPrice = 0.97;
	private static final double gPrice = 1.2;
	private static final double mPrice = 1;
	private static final Integer user = 13;
	private static final String timeStamp = "12:48";
	private static final double dependability = 1.2;

	private GasStationRepository gsRepository;
	private GasStationConverter gsConverter;
	private UserRepository userRepository;
	private GasStationService gsService;
	private GasStation gs;
	private GasStationDto gsd;
	
	
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
		String Pass = "testpass";
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
	
	//GasStationServiceimpl
	
	@Test
	public void saveGasStationTest() throws PriceException, GPSDataException {
		gsRepository = mock(GasStationRepository.class);
		when(gsRepository.findAll()).thenReturn(new ArrayList<GasStation>());

		gs = new GasStation(name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon, dPrice,
				sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsd = new GasStationDto(id, name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon,
				dPrice, sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsConverter = mock(GasStationConverter.class);
		when(gsConverter.toGasStationDto(gs)).thenReturn(gsd);
		when(gsConverter.toGasStation(gsd)).thenReturn(gs);

//		userRepository = mock(UserRepository.class);
//		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		gsService = new GasStationServiceimpl(gsRepository, gsConverter, userRepository);

		GasStationDto obtainedDto = gsService.saveGasStation(gsd);

		assertEquals(gsd, obtainedDto);
	}

}




