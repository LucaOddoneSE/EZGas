package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.impl.GasStationServiceimpl;

@RunWith(SpringRunner.class)
@SpringBootTest

public class GasStationServiceimplTest {

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

	/*
	 * @BeforeEach public void setUp() { gsService = new GasStationServiceimpl(); }
	 */

	/*
	 * @Test public void sizeTest() throws Exception { gsRepository =
	 * mock(GasStationRepository.class); when(gsRepository.findAll()).thenReturn(new
	 * ArrayList<GasStation>());
	 * 
	 * gsService = new GasStationServiceimpl(gsRepository);
	 * 
	 * ArrayList<GasStationDto> gsarray = (ArrayList) gsService.getAllGasStations();
	 * 
	 * assert (gsarray.size() == 0); }
	 */

	@Test
	public void getGasStationByIdTest() throws InvalidGasStationException, PriceException, GPSDataException {
		gs = new GasStation(name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon, dPrice,
				sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		// gs.setGasStationId(id);

		gsd = new GasStationDto(id, name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon,
				dPrice, sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsConverter = mock(GasStationConverter.class);
		when(gsConverter.toGasStationDto(gs)).thenReturn(gsd);
		when(gsConverter.toGasStation(gsd)).thenReturn(gs);

		gsRepository = mock(GasStationRepository.class);
		when(gsRepository.findAll()).thenReturn(new ArrayList<GasStation>());

		userRepository = mock(UserRepository.class);
		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		gsService = new GasStationServiceimpl(gsRepository, gsConverter, userRepository);

		gsd = gsService.saveGasStation(gsd);

		GasStationDto obtainedDto = gsService.getGasStationById(id);

		assertEquals(gsd, obtainedDto);
	}

	@Test
	public void saveGasStationTest() throws PriceException, GPSDataException {
		gsRepository = mock(GasStationRepository.class);
		when(gsRepository.findAll()).thenReturn(new ArrayList<GasStation>());

		gs = new GasStation(name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon, dPrice,
				sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		// gs.getGasStationId();

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

	@Test
	public void getAllGasStationsTest() throws PriceException, GPSDataException {
		gs = new GasStation(name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon, dPrice,
				sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gs.setGasStationId(id);

		gsd = new GasStationDto(id, name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon,
				dPrice, sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsConverter = mock(GasStationConverter.class);
		when(gsConverter.toGasStationDto(gs)).thenReturn(gsd);

		gsRepository = mock(GasStationRepository.class);
		when(gsRepository.findAll()).thenReturn(new ArrayList<GasStation>());

//		userRepository = mock(UserRepository.class);
//		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		gsService = new GasStationServiceimpl(gsRepository, gsConverter, userRepository);

		gsd = gsService.saveGasStation(gsd);

		// ArrayList<GasStation> listRep = (ArrayList<GasStation>)
		// gsRepository.findAll();

		ArrayList<GasStationDto> expectedList = new ArrayList<>();
		ArrayList<GasStationDto> obtainedList = new ArrayList<>();

		expectedList.add(gsd);
		obtainedList = (ArrayList) gsService.getAllGasStations();

		assertEquals(expectedList, obtainedList);
	}

	@Test
	public void deleteGasStationTest() throws InvalidGasStationException, PriceException, GPSDataException {
		gs = new GasStation(name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon, dPrice,
				sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsd = new GasStationDto(id, name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon,
				dPrice, sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsConverter = mock(GasStationConverter.class);
		when(gsConverter.toGasStationDto(gs)).thenReturn(gsd);

		gsRepository = mock(GasStationRepository.class);
		when(gsRepository.findAll()).thenReturn(new ArrayList<GasStation>());

//		userRepository = mock(UserRepository.class);
//		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		gsService = new GasStationServiceimpl(gsRepository, gsConverter, userRepository);

		gsd = gsService.saveGasStation(gsd);

		Boolean expectedValue = true;
		Boolean obtainedValue = gsService.deleteGasStation(id);

		assertEquals(expectedValue, obtainedValue);

	}

	@Test
	public void getGasStationsByGasolineTypeTest() throws InvalidGasTypeException, PriceException, GPSDataException {
		gs = new GasStation(name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon, dPrice,
				sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsd = new GasStationDto(id, name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon,
				dPrice, sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsConverter = mock(GasStationConverter.class);
		when(gsConverter.toGasStationDto(gs)).thenReturn(gsd);

		gsRepository = mock(GasStationRepository.class);
		when(gsRepository.findAll()).thenReturn(new ArrayList<GasStation>());

//		userRepository = mock(UserRepository.class);
//		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		gsService = new GasStationServiceimpl(gsRepository, gsConverter, userRepository);

		gsd = gsService.saveGasStation(gsd);

		String type = "Diesel";

		ArrayList<GasStationDto> expectedList = new ArrayList<>();
		ArrayList<GasStationDto> obtainedList = new ArrayList<>();

		expectedList.add(gsd);
		obtainedList = (ArrayList<GasStationDto>) gsService.getGasStationsByGasolineType(type);

		assertEquals(expectedList, obtainedList);

	}

	@Test
	public void getGasStationsByProximityTest() throws GPSDataException, PriceException {
		gs = new GasStation(name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon, dPrice,
				sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsd = new GasStationDto(id, name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon,
				dPrice, sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsConverter = mock(GasStationConverter.class);
		when(gsConverter.toGasStationDto(gs)).thenReturn(gsd);

		gsRepository = mock(GasStationRepository.class);
		when(gsRepository.findAll()).thenReturn(new ArrayList<GasStation>());

//		userRepository = mock(UserRepository.class);
//		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		gsService = new GasStationServiceimpl(gsRepository, gsConverter, userRepository);

		gsd = gsService.saveGasStation(gsd);

		ArrayList<GasStationDto> expectedList = new ArrayList<>();
		ArrayList<GasStationDto> obtainedList = new ArrayList<>();

		expectedList.add(gsd);
		obtainedList = (ArrayList<GasStationDto>) gsService.getGasStationsByProximity(lat, lon);

		assertEquals(expectedList, obtainedList);

	}

	@Test
	public void getGasStationsWithCoordinatesTest() throws InvalidGasTypeException, GPSDataException, PriceException {
		gs = new GasStation(name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon, dPrice,
				sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsd = new GasStationDto(id, name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon,
				dPrice, sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsConverter = mock(GasStationConverter.class);
		when(gsConverter.toGasStationDto(gs)).thenReturn(gsd);

		gsRepository = mock(GasStationRepository.class);
		when(gsRepository.findAll()).thenReturn(new ArrayList<GasStation>());

//		userRepository = mock(UserRepository.class);
//		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		gsService = new GasStationServiceimpl(gsRepository, gsConverter, userRepository);

		gsd = gsService.saveGasStation(gsd);

		String type = "Diesel";

		ArrayList<GasStationDto> expectedList = new ArrayList<>();
		ArrayList<GasStationDto> obtainedList = new ArrayList<>();

		expectedList.add(gsd);
		gsService.getGasStationsWithCoordinates(lat, lon, type, carSharing);

		assertEquals(expectedList, obtainedList);

	}

	@Test
	public void getGasStationsWithoutCoordinatesTest()
			throws InvalidGasTypeException, PriceException, GPSDataException {
		gs = new GasStation(name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon, dPrice,
				sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsd = new GasStationDto(id, name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon,
				dPrice, sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsConverter = mock(GasStationConverter.class);
		when(gsConverter.toGasStationDto(gs)).thenReturn(gsd);

		gsRepository = mock(GasStationRepository.class);
		when(gsRepository.findAll()).thenReturn(new ArrayList<GasStation>());

//		userRepository = mock(UserRepository.class);
//		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		gsService = new GasStationServiceimpl(gsRepository, gsConverter, userRepository);

		gsd = gsService.saveGasStation(gsd);

		String type = "Diesel";

		ArrayList<GasStationDto> expectedList = new ArrayList<>();
		ArrayList<GasStationDto> obtainedList = new ArrayList<>();

		expectedList.add(gsd);
		gsService.getGasStationsWithoutCoordinates(type, carSharing);

		assertEquals(expectedList, obtainedList);

	}

	@Test
	public void getGasStationByCarSharingTest() throws PriceException, GPSDataException {
		gs = new GasStation(name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon, dPrice,
				sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsd = new GasStationDto(id, name, address, diesel, super1, superPlus, gas, methane, carSharing, lat, lon,
				dPrice, sPrice, spPrice, gPrice, mPrice, user, timeStamp, dependability);

		gsConverter = mock(GasStationConverter.class);
		when(gsConverter.toGasStationDto(gs)).thenReturn(gsd);

		gsRepository = mock(GasStationRepository.class);
		when(gsRepository.findAll()).thenReturn(new ArrayList<GasStation>());

//		userRepository = mock(UserRepository.class);
//		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		gsService = new GasStationServiceimpl(gsRepository, gsConverter, userRepository);

		gsd = gsService.saveGasStation(gsd);

		ArrayList<GasStationDto> expectedList = new ArrayList<>();
		ArrayList<GasStationDto> obtainedList = new ArrayList<>();

		expectedList.add(gsd);
		gsService.getGasStationByCarSharing(carSharing);

		assertEquals(expectedList, obtainedList);
	}

}