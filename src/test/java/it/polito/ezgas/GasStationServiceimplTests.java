package it.polito.ezgas.service.implTests;

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
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.impl.GasStationServiceimpl;

@RunWith(SpringRunner.class)
@SpringBootTest

public class GasStationServiceimplTests {

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

