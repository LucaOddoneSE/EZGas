package it.polito.ezgas.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import exception.InvalidGasStationException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;

public class GasStationServiceImplTests {
	
	@Mock
	private GasStationServiceimpl gasStationServiceImplMock = mock(GasStationServiceimpl.class);
	@Mock
	private GasStationRepository gasStationRepositoryMock = mock(GasStationRepository.class);
	@Mock
	private UserRepository userRepositoryMock = mock(UserRepository.class);
	@Mock
	private GasStationConverter gasStationConverterMock = mock(GasStationConverter.class);
	
	private List<GasStationDto> listGasStationDto = new ArrayList<>();
	
	private List<GasStation> listGasStation = new ArrayList<>();
	
	//Empty DB
	@Test
	public void testgetGasStationByIdEmptyDatabase() throws InvalidGasStationException {
		listGasStationDto.clear();
		listGasStation.clear();
		
		when(gasStationRepositoryMock.findOne(1)).thenAnswer(invocation -> {
			Iterator<GasStation> iter = listGasStation.iterator();
			while(iter.hasNext()) {
				GasStation gasStation = iter.next();
				if(gasStation.getGasStationId() == 1)
					return gasStation;
			}
			return null;
		});
		
		when(gasStationConverterMock.toGasStationDto(gasStationRepositoryMock.findOne(1))).
		thenAnswer(invocazione -> {
			if(gasStationRepositoryMock.findOne(1) == null)
				return null;
			GasStationDto gasStationDto = new GasStationDto(gasStationRepositoryMock.findOne(1).getGasStationId(),
					gasStationRepositoryMock.findOne(1).getGasStationName(),
					gasStationRepositoryMock.findOne(1).getGasStationAddress(),
					gasStationRepositoryMock.findOne(1).getHasDiesel(), 
					gasStationRepositoryMock.findOne(1).getHasSuper(),
					gasStationRepositoryMock.findOne(1).getHasSuperPlus(),
					gasStationRepositoryMock.findOne(1).getHasGas(),
					gasStationRepositoryMock.findOne(1).getHasMethane(),
					gasStationRepositoryMock.findOne(1).getCarSharing(),
					gasStationRepositoryMock.findOne(1).getLat(),
					gasStationRepositoryMock.findOne(1).getLon(),
					gasStationRepositoryMock.findOne(1).getDieselPrice(),
					gasStationRepositoryMock.findOne(1).getSuperPrice(),
					gasStationRepositoryMock.findOne(1).getSuperPlusPrice(),
					gasStationRepositoryMock.findOne(1).getGasPrice(),
					gasStationRepositoryMock.findOne(1).getMethanePrice(),
					gasStationRepositoryMock.findOne(1).getReportUser(),
					gasStationRepositoryMock.findOne(1).getReportTimestamp(),
					gasStationRepositoryMock.findOne(1).getReportDependability() );
			return gasStationDto;
		});
		
		when(gasStationServiceImplMock.getGasStationById(1)).
		thenAnswer(invocazione -> {
			return gasStationConverterMock.toGasStationDto(gasStationRepositoryMock.findOne(1));
		});
		
		when(gasStationRepositoryMock.findOne(2)).thenAnswer(invocation -> {
			Iterator<GasStation> iter = listGasStation.iterator();
			while(iter.hasNext()) {
				GasStation gasStation = iter.next();
				if(gasStation.getGasStationId() == 2)
					return gasStation;
			}
			return null;
		});
		
		when(gasStationConverterMock.toGasStationDto(gasStationRepositoryMock.findOne(2))).
		thenAnswer(invocazione -> {
			if(gasStationRepositoryMock.findOne(2) == null)
				return null;
			GasStationDto gasStationDto = new GasStationDto(gasStationRepositoryMock.findOne(2).getGasStationId(),
					gasStationRepositoryMock.findOne(2).getGasStationName(),
					gasStationRepositoryMock.findOne(2).getGasStationAddress(),
					gasStationRepositoryMock.findOne(2).getHasDiesel(), 
					gasStationRepositoryMock.findOne(2).getHasSuper(),
					gasStationRepositoryMock.findOne(2).getHasSuperPlus(),
					gasStationRepositoryMock.findOne(2).getHasGas(),
					gasStationRepositoryMock.findOne(2).getHasMethane(),
					gasStationRepositoryMock.findOne(2).getCarSharing(),
					gasStationRepositoryMock.findOne(2).getLat(),
					gasStationRepositoryMock.findOne(2).getLon(),
					gasStationRepositoryMock.findOne(2).getDieselPrice(),
					gasStationRepositoryMock.findOne(2).getSuperPrice(),
					gasStationRepositoryMock.findOne(2).getSuperPlusPrice(),
					gasStationRepositoryMock.findOne(2).getGasPrice(),
					gasStationRepositoryMock.findOne(2).getMethanePrice(),
					gasStationRepositoryMock.findOne(2).getReportUser(),
					gasStationRepositoryMock.findOne(2).getReportTimestamp(),
					gasStationRepositoryMock.findOne(2).getReportDependability() );
			return gasStationDto;
		});
		
		when(gasStationServiceImplMock.getGasStationById(2)).
		thenAnswer(invocazione -> {
			return gasStationConverterMock.toGasStationDto(gasStationRepositoryMock.findOne(2));
		});
		
		assertNull(gasStationServiceImplMock.getGasStationById(1));
		assertNull(gasStationServiceImplMock.getGasStationById(2));
	}
	
	//Retrieve Existing GasStationById
	@Test
	public void testgetGasStationByIdExistingGasStation() throws InvalidGasStationException {
		
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",110.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",110.649,107.550,0,0,1.25,1.55,0,null,null,0);
		
		gasStation1.setGasStationId(1);
		gasStation2.setGasStationId(2);
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		listGasStation.add(gasStation1);
		listGasStation.add(gasStation2);
		
		when(gasStationRepositoryMock.findOne(1)).thenAnswer(invocation -> {
			Iterator<GasStation> iter = listGasStation.iterator();
			while(iter.hasNext()) {
				GasStation gasStation = iter.next();
				if(gasStation.getGasStationId() == 1)
					return gasStation;
			}
			return null;
		});
		
		when(gasStationConverterMock.toGasStationDto(gasStationRepositoryMock.findOne(1))).
		thenAnswer(invocazione -> {
			if(gasStationRepositoryMock.findOne(1) == null)
				return null;
			GasStationDto gasStationDto = new GasStationDto(gasStationRepositoryMock.findOne(1).getGasStationId(),
					gasStationRepositoryMock.findOne(1).getGasStationName(),
					gasStationRepositoryMock.findOne(1).getGasStationAddress(),
					gasStationRepositoryMock.findOne(1).getHasDiesel(), 
					gasStationRepositoryMock.findOne(1).getHasSuper(),
					gasStationRepositoryMock.findOne(1).getHasSuperPlus(),
					gasStationRepositoryMock.findOne(1).getHasGas(),
					gasStationRepositoryMock.findOne(1).getHasMethane(),
					gasStationRepositoryMock.findOne(1).getCarSharing(),
					gasStationRepositoryMock.findOne(1).getLat(),
					gasStationRepositoryMock.findOne(1).getLon(),
					gasStationRepositoryMock.findOne(1).getDieselPrice(),
					gasStationRepositoryMock.findOne(1).getSuperPrice(),
					gasStationRepositoryMock.findOne(1).getSuperPlusPrice(),
					gasStationRepositoryMock.findOne(1).getGasPrice(),
					gasStationRepositoryMock.findOne(1).getMethanePrice(),
					gasStationRepositoryMock.findOne(1).getReportUser(),
					gasStationRepositoryMock.findOne(1).getReportTimestamp(),
					gasStationRepositoryMock.findOne(1).getReportDependability() );
			return gasStationDto;
		});
		
		when(gasStationServiceImplMock.getGasStationById(1)).
		thenAnswer(invocazione -> {
			return gasStationConverterMock.toGasStationDto(gasStationRepositoryMock.findOne(1));
		});
		
		when(gasStationRepositoryMock.findOne(2)).thenAnswer(invocation -> {
			Iterator<GasStation> iter = listGasStation.iterator();
			while(iter.hasNext()) {
				GasStation gasStation = iter.next();
				if(gasStation.getGasStationId() == 2)
					return gasStation;
			}
			return null;
		});
		
		when(gasStationConverterMock.toGasStationDto(gasStationRepositoryMock.findOne(2))).
		thenAnswer(invocazione -> {
			if(gasStationRepositoryMock.findOne(2) == null)
				return null;
			GasStationDto gasStationDto = new GasStationDto(gasStationRepositoryMock.findOne(2).getGasStationId(),
					gasStationRepositoryMock.findOne(2).getGasStationName(),
					gasStationRepositoryMock.findOne(2).getGasStationAddress(),
					gasStationRepositoryMock.findOne(2).getHasDiesel(), 
					gasStationRepositoryMock.findOne(2).getHasSuper(),
					gasStationRepositoryMock.findOne(2).getHasSuperPlus(),
					gasStationRepositoryMock.findOne(2).getHasGas(),
					gasStationRepositoryMock.findOne(2).getHasMethane(),
					gasStationRepositoryMock.findOne(2).getCarSharing(),
					gasStationRepositoryMock.findOne(2).getLat(),
					gasStationRepositoryMock.findOne(2).getLon(),
					gasStationRepositoryMock.findOne(2).getDieselPrice(),
					gasStationRepositoryMock.findOne(2).getSuperPrice(),
					gasStationRepositoryMock.findOne(2).getSuperPlusPrice(),
					gasStationRepositoryMock.findOne(2).getGasPrice(),
					gasStationRepositoryMock.findOne(2).getMethanePrice(),
					gasStationRepositoryMock.findOne(2).getReportUser(),
					gasStationRepositoryMock.findOne(2).getReportTimestamp(),
					gasStationRepositoryMock.findOne(2).getReportDependability() );
			return gasStationDto;
		});
		
		when(gasStationServiceImplMock.getGasStationById(2)).
		thenAnswer(invocazione -> {
			return gasStationConverterMock.toGasStationDto(gasStationRepositoryMock.findOne(2));
		});
		
		assertEquals(Integer.valueOf(1),gasStationServiceImplMock.getGasStationById(1).getGasStationId());
		assertEquals(Integer.valueOf(2),gasStationServiceImplMock.getGasStationById(2).getGasStationId());
	}
	
	//passing UserId < 0 to the method getGasStationById()
	@Test(expected=InvalidGasStationException.class)
	public void testgetGasStationByIdNegativeUserId() throws InvalidGasStationException{
		
		when(gasStationServiceImplMock.getGasStationById(-10)).thenThrow(
				new InvalidGasStationException("Error! You have passed a negative GasStationId") );
		
		gasStationServiceImplMock.getGasStationById(-10);
	}
}
