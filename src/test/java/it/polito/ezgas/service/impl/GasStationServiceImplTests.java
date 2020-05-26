package it.polito.ezgas.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.utils.Day;
import it.polito.ezgas.utils.Haversine;

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
				"BlaBlaCar",110.574,81.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",110.649,87.550,0,0,1.25,1.55,0,null,null,0);
		
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
	
	//Save a new GasStation
	@Test
	public void testSaveGasStationNewOne() throws PriceException, GPSDataException {
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStationDto gasStation2Dto = new GasStationDto(2,"GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
	
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		
		when(gasStationConverterMock.toGasStationDto(gasStation2)).thenReturn(gasStation2Dto);
		when(gasStationConverterMock.toGasStation(gasStation2Dto)).thenReturn(gasStation2);
		
		when(gasStationServiceImplMock.saveGasStation(gasStation1Dto)).thenAnswer(invocation -> {
			Integer GasStationId = gasStation1Dto.getGasStationId();
			when(gasStationRepositoryMock.findOne(GasStationId)).thenAnswer( inv -> {
				Iterator<GasStation> iter = listGasStation.iterator();
				while(iter.hasNext()) {
					GasStation station = iter.next();
					if(station.getGasStationId() == GasStationId)
						return station;
				}
				return null;
			});
			if(gasStationRepositoryMock.findOne(GasStationId) == null) {
				listGasStation.add(gasStationConverterMock.toGasStation(gasStation1Dto));
				return gasStation1Dto;
			}
			GasStation station = gasStationRepositoryMock.findOne(GasStationId);
			station.setGasStationName(gasStation1Dto.getGasStationName());
			station.setGasStationAddress(gasStation1Dto.getGasStationAddress());
			station.setHasDiesel(gasStation1Dto.getHasDiesel()); 
			station.setHasSuper(gasStation1Dto.getHasSuper());
			station.setHasSuperPlus(gasStation1Dto.getHasSuperPlus());
			station.setHasGas(gasStation1Dto.getHasGas());
			station.setHasMethane(gasStation1Dto.getHasMethane());
			station.setCarSharing(gasStation1Dto.getCarSharing());
			station.setLat(gasStation1Dto.getLat());
			station.setLon(gasStation1Dto.getLon());
			station.setDieselPrice(gasStation1Dto.getDieselPrice());
			station.setSuperPrice(gasStation1Dto.getSuperPrice());
			station.setSuperPlusPrice(gasStation1Dto.getSuperPlusPrice());
			station.setGasPrice(gasStation1Dto.getGasPrice());
			station.setMethanePrice(gasStation1Dto.getMethanePrice());
			station.setReportUser(gasStation1Dto.getReportUser());
			station.setReportTimestamp(gasStation1Dto.getReportTimestamp());
			station.setReportDependability(gasStation1Dto.getReportDependability());
			return gasStation1Dto;
		});
		
		when(gasStationServiceImplMock.saveGasStation(gasStation2Dto)).thenAnswer(invocation -> {
			Integer GasStationId = gasStation2Dto.getGasStationId();
			when(gasStationRepositoryMock.findOne(GasStationId)).thenAnswer( inv -> {
				Iterator<GasStation> iter = listGasStation.iterator();
				while(iter.hasNext()) {
					GasStation station = iter.next();
					if(station.getGasStationId() == GasStationId)
						return station;
				}
				return null;
			});
			if(gasStationRepositoryMock.findOne(GasStationId) == null) {
				listGasStation.add(gasStationConverterMock.toGasStation(gasStation2Dto));
				return gasStation2Dto;
			}
			GasStation station = gasStationRepositoryMock.findOne(GasStationId);
			station.setGasStationName(gasStation2Dto.getGasStationName());
			station.setGasStationAddress(gasStation2Dto.getGasStationAddress());
			station.setHasDiesel(gasStation2Dto.getHasDiesel()); 
			station.setHasSuper(gasStation2Dto.getHasSuper());
			station.setHasSuperPlus(gasStation2Dto.getHasSuperPlus());
			station.setHasGas(gasStation2Dto.getHasGas());
			station.setHasMethane(gasStation2Dto.getHasMethane());
			station.setCarSharing(gasStation2Dto.getCarSharing());
			station.setLat(gasStation2Dto.getLat());
			station.setLon(gasStation2Dto.getLon());
			station.setDieselPrice(gasStation2Dto.getDieselPrice());
			station.setSuperPrice(gasStation2Dto.getSuperPrice());
			station.setSuperPlusPrice(gasStation2Dto.getSuperPlusPrice());
			station.setGasPrice(gasStation2Dto.getGasPrice());
			station.setMethanePrice(gasStation2Dto.getMethanePrice());
			station.setReportUser(gasStation2Dto.getReportUser());
			station.setReportTimestamp(gasStation2Dto.getReportTimestamp());
			station.setReportDependability(gasStation2Dto.getReportDependability());
			return gasStation2Dto;
		});
		
		gasStationServiceImplMock.saveGasStation(gasStation1Dto);
		
		assertEquals(1,listGasStation.size());
		
		gasStationServiceImplMock.saveGasStation(gasStation2Dto);
		
		assertEquals(2,listGasStation.size());
	}

    //Save a new GasStation throw PriceException
	@Test(expected=PriceException.class)
	public void testSaveGasStationPriceException() throws PriceException, GPSDataException {
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,-1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,-1.25,1.55,0,0,0.90,null,null,0);
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		
		when(gasStationServiceImplMock.saveGasStation(gasStation1Dto)).thenAnswer(invocation -> {
			Integer GasStationId = gasStation1Dto.getGasStationId();
			
			if( (gasStation1Dto.getHasDiesel() && gasStation1Dto.getDieselPrice() < 0) || 
					(gasStation1Dto.getHasGas() && gasStation1Dto.getGasPrice() < 0  ) || 
				    (gasStation1Dto.getHasSuper() && gasStation1Dto.getSuperPrice() < 0 ) ||
				    (gasStation1Dto.getHasSuperPlus() &&  gasStation1Dto.getSuperPlusPrice() < 0 ) || 
				    (gasStation1Dto.getHasMethane() && gasStation1Dto.getMethanePrice() < 0) ) 
					throw new PriceException("Error! One or more of the fuel types price is negative!");
			
			if( (gasStation1Dto.getLon() < -180 || gasStation1Dto.getLon() >= 180) || 
					(gasStation1Dto.getLat() < -90 || gasStation1Dto.getLat() >= 90) )
					throw new GPSDataException("Error! GasStation containes wrong coordinates values");
			
			when(gasStationRepositoryMock.findOne(GasStationId)).thenAnswer( inv -> {
				Iterator<GasStation> iter = listGasStation.iterator();
				while(iter.hasNext()) {
					GasStation station = iter.next();
					if(station.getGasStationId() == GasStationId)
						return station;
				}
				return null;
			});
			
			if(gasStationRepositoryMock.findOne(GasStationId) == null) {
				listGasStation.add(gasStationConverterMock.toGasStation(gasStation1Dto));
				return gasStation1Dto;
			}
			GasStation station = gasStationRepositoryMock.findOne(GasStationId);
			station.setGasStationName(gasStation1Dto.getGasStationName());
			station.setGasStationAddress(gasStation1Dto.getGasStationAddress());
			station.setHasDiesel(gasStation1Dto.getHasDiesel()); 
			station.setHasSuper(gasStation1Dto.getHasSuper());
			station.setHasSuperPlus(gasStation1Dto.getHasSuperPlus());
			station.setHasGas(gasStation1Dto.getHasGas());
			station.setHasMethane(gasStation1Dto.getHasMethane());
			station.setCarSharing(gasStation1Dto.getCarSharing());
			station.setLat(gasStation1Dto.getLat());
			station.setLon(gasStation1Dto.getLon());
			station.setDieselPrice(gasStation1Dto.getDieselPrice());
			station.setSuperPrice(gasStation1Dto.getSuperPrice());
			station.setSuperPlusPrice(gasStation1Dto.getSuperPlusPrice());
			station.setGasPrice(gasStation1Dto.getGasPrice());
			station.setMethanePrice(gasStation1Dto.getMethanePrice());
			station.setReportUser(gasStation1Dto.getReportUser());
			station.setReportTimestamp(gasStation1Dto.getReportTimestamp());
			station.setReportDependability(gasStation1Dto.getReportDependability());
			return gasStation1Dto;
		});
		
		gasStationServiceImplMock.saveGasStation(gasStation1Dto);
	}

    //Save a new GasStation throw GPSDataException
	@Test(expected=GPSDataException.class)
	public void testSaveGasStationGPSDataException() throws PriceException, GPSDataException {
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",181.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",181.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		
		when(gasStationServiceImplMock.saveGasStation(gasStation1Dto)).thenAnswer(invocation -> {
			Integer GasStationId = gasStation1Dto.getGasStationId();
			
			if( (gasStation1Dto.getHasDiesel() && gasStation1Dto.getDieselPrice() < 0) || 
					(gasStation1Dto.getHasGas() && gasStation1Dto.getGasPrice() < 0  ) || 
				    (gasStation1Dto.getHasSuper() && gasStation1Dto.getSuperPrice() < 0 ) ||
				    (gasStation1Dto.getHasSuperPlus() &&  gasStation1Dto.getSuperPlusPrice() < 0 ) || 
				    (gasStation1Dto.getHasMethane() && gasStation1Dto.getMethanePrice() < 0) ) 
					throw new PriceException("Error! One or more of the fuel types price is negative!");
			
			if( (gasStation1Dto.getLon() < -180 || gasStation1Dto.getLon() >= 180) || 
					(gasStation1Dto.getLat() < -90 || gasStation1Dto.getLat() >= 90) )
					throw new GPSDataException("Error! GasStation containes wrong coordinates values");
			
			when(gasStationRepositoryMock.findOne(GasStationId)).thenAnswer( inv -> {
				Iterator<GasStation> iter = listGasStation.iterator();
				while(iter.hasNext()) {
					GasStation station = iter.next();
					if(station.getGasStationId() == GasStationId)
						return station;
				}
				return null;
			});
			
			if(gasStationRepositoryMock.findOne(GasStationId) == null) {
				listGasStation.add(gasStationConverterMock.toGasStation(gasStation1Dto));
				return gasStation1Dto;
			}
			GasStation station = gasStationRepositoryMock.findOne(GasStationId);
			station.setGasStationName(gasStation1Dto.getGasStationName());
			station.setGasStationAddress(gasStation1Dto.getGasStationAddress());
			station.setHasDiesel(gasStation1Dto.getHasDiesel()); 
			station.setHasSuper(gasStation1Dto.getHasSuper());
			station.setHasSuperPlus(gasStation1Dto.getHasSuperPlus());
			station.setHasGas(gasStation1Dto.getHasGas());
			station.setHasMethane(gasStation1Dto.getHasMethane());
			station.setCarSharing(gasStation1Dto.getCarSharing());
			station.setLat(gasStation1Dto.getLat());
			station.setLon(gasStation1Dto.getLon());
			station.setDieselPrice(gasStation1Dto.getDieselPrice());
			station.setSuperPrice(gasStation1Dto.getSuperPrice());
			station.setSuperPlusPrice(gasStation1Dto.getSuperPlusPrice());
			station.setGasPrice(gasStation1Dto.getGasPrice());
			station.setMethanePrice(gasStation1Dto.getMethanePrice());
			station.setReportUser(gasStation1Dto.getReportUser());
			station.setReportTimestamp(gasStation1Dto.getReportTimestamp());
			station.setReportDependability(gasStation1Dto.getReportDependability());
			return gasStation1Dto;
		});
		
		gasStationServiceImplMock.saveGasStation(gasStation1Dto);
	}
	
	@Test
	public void testGetAllGasStations() {
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStationDto gasStation2Dto = new GasStationDto(2,"GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		gasStation2.setGasStationId(2);
		
		listGasStation.add(gasStation1);
		listGasStation.add(gasStation2);
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStationDto(gasStation2)).thenReturn(gasStation2Dto);
		
		when(gasStationServiceImplMock.getAllGasStations()).then( invocation -> {
			Iterator<GasStation> iter = listGasStation.listIterator();
			while(iter.hasNext()) {
				GasStation gasStation = iter.next();
				if(gasStation.getGasStationId() == 1)
					listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation1));
				else
					listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation2));
			}
			return listGasStationDto;
		});
		
		gasStationServiceImplMock.getAllGasStations();
		
		assertEquals(2,listGasStationDto.size());
	}
	
	@Test
	public void testDeleteGasStation() throws InvalidGasStationException {
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		gasStation2.setGasStationId(2);
		
		listGasStation.add(gasStation1);
		listGasStation.add(gasStation2);
		
		Integer int1 = gasStation1.getGasStationId();
		Integer int2 = gasStation2.getGasStationId();
		
		when(gasStationServiceImplMock.deleteGasStation(int1)).thenAnswer( invocation -> {
			doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) {
					Iterator<GasStation> iter = listGasStation.listIterator();
					while(iter.hasNext()) {
						GasStation gasStation = iter.next();
						if(gasStation.getGasStationId() == int1) {
							listGasStation.remove(gasStation1);
							break;
						}
					}
					return null;
				}
			}).when(gasStationRepositoryMock).delete(int1);
			gasStationRepositoryMock.delete(int1);
			return null;
		});
		
		when(gasStationServiceImplMock.deleteGasStation(int2)).thenAnswer( invocation -> {
			doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) {
					Iterator<GasStation> iter = listGasStation.listIterator();
					while(iter.hasNext()) {
						GasStation gasStation = iter.next();
						if(gasStation.getGasStationId() == int2) {
							listGasStation.remove(gasStation2);
							break;
						}
					}
					return null;
				}
			}).when(gasStationRepositoryMock).delete(int2);
			gasStationRepositoryMock.delete(int2);
		return null;
		});
		
		gasStationServiceImplMock.deleteGasStation(int1);
		
		assertEquals(1,listGasStation.size());
		
		gasStationServiceImplMock.deleteGasStation(int2);
		
		assertEquals(0,listGasStation.size());
	}
	
	//Delete method throw InvalidGasStationException
	@Test(expected=InvalidGasStationException.class)
	public void testDeleteGasStationInvalidGasStationException() throws InvalidGasStationException {
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		
		listGasStation.add(gasStation1);
		
		Integer int1 = -1;
		
		when(gasStationServiceImplMock.deleteGasStation(int1)).thenAnswer( invocation -> {
			if(int1 < 0)
				throw new InvalidGasStationException("Error! It has been passed an invalid gasStationId(<0)");
			doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) {
					Iterator<GasStation> iter = listGasStation.listIterator();
					while(iter.hasNext()) {
						GasStation gasStation = iter.next();
						if(gasStation.getGasStationId() == int1) {
							listGasStation.remove(gasStation1);
							break;
						}
					}
					return null;
				}
			}).when(gasStationRepositoryMock).delete(int1);
			gasStationRepositoryMock.delete(int1);
			return null;
		});
		
		gasStationServiceImplMock.deleteGasStation(int1);
		
	}
	
	//Retrieve GasStationsByGasolineType
	@Test
	public void testGasStationsByGasolineType() throws InvalidGasTypeException {
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStationDto gasStation2Dto = new GasStationDto(2,"GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
	
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
		
		String gasolinetype = "Diesel";
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		gasStation2.setGasStationId(2);
		
		listGasStation.add(gasStation1);
		listGasStation.add(gasStation2);
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStationDto(gasStation2)).thenReturn(gasStation2Dto);
		
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		when(gasStationConverterMock.toGasStation(gasStation2Dto)).thenReturn(gasStation2);
		
		when(gasStationServiceImplMock.getGasStationsByGasolineType(gasolinetype)).thenAnswer(invocation -> {
			Iterator<GasStation> iteratore;
			when(gasStationRepositoryMock.findAll()).thenAnswer(inv -> {
				Iterator<GasStation> iter = listGasStation.listIterator();
				while(iter.hasNext()) {
					GasStation gasStation = iter.next();
					if(gasStation.getGasStationId() == 1)
						listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation1));
					else
						listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation2));
				}
				return null;
			});
			switch(gasolinetype) {
			  case "Diesel":
				  gasStationRepositoryMock.findAll();
				  iteratore = listGasStation.iterator();
				  while(iteratore.hasNext()) {
					  GasStation gasStation = iteratore.next();
					  if(gasStation.getHasDiesel() == false) {
						  if(gasStation.getGasStationId() == 1)
							  listGasStationDto.remove(gasStationConverterMock.toGasStationDto(gasStation1));
						  else
							  listGasStationDto.remove(gasStationConverterMock.toGasStationDto(gasStation2));
					  }
				  }
			    break;
			  default:
					throw new InvalidGasTypeException("Error! You have passed a non valid gasolinetype as parameter");
			}
			return null;
		});
		
		gasStationServiceImplMock.getGasStationsByGasolineType(gasolinetype);
		
		assertEquals(1,listGasStationDto.size());
		
		if(listGasStationDto.get(0).getHasDiesel() == false)
			assertEquals(3,listGasStationDto.size());
	}
	
	//Throw InvalidGasTypeException
	@Test(expected = InvalidGasTypeException.class)
	public void testGasStationsByGasolineTypeException() throws InvalidGasTypeException {
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStationDto gasStation2Dto = new GasStationDto(2,"GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
	
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
		
		String gasolinetype = "Fuel";
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		gasStation2.setGasStationId(2);
		
		listGasStation.add(gasStation1);
		listGasStation.add(gasStation2);
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStationDto(gasStation2)).thenReturn(gasStation2Dto);
		
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		when(gasStationConverterMock.toGasStation(gasStation2Dto)).thenReturn(gasStation2);
		
		when(gasStationServiceImplMock.getGasStationsByGasolineType(gasolinetype)).thenAnswer(invocation -> {
			Iterator<GasStation> iteratore;
			when(gasStationRepositoryMock.findAll()).thenAnswer(inv -> {
				Iterator<GasStation> iter = listGasStation.listIterator();
				while(iter.hasNext()) {
					GasStation gasStation = iter.next();
					if(gasStation.getGasStationId() == 1)
						listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation1));
					else
						listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation2));
				}
				return null;
			});
			switch(gasolinetype) {
			  case "Diesel":
				  gasStationRepositoryMock.findAll();
				  iteratore = listGasStation.iterator();
				  while(iteratore.hasNext()) {
					  GasStation gasStation = iteratore.next();
					  if(gasStation.getHasDiesel() == false) {
						  if(gasStation.getGasStationId() == 1)
							  listGasStationDto.remove(gasStationConverterMock.toGasStationDto(gasStation1));
						  else
							  listGasStationDto.remove(gasStationConverterMock.toGasStationDto(gasStation2));
					  }
				  }
			    break;
			  default:
					throw new InvalidGasTypeException("Error! You have passed a non valid gasolinetype as parameter");
			}
			return null;
		});
		
		gasStationServiceImplMock.getGasStationsByGasolineType(gasolinetype);
	}
	
	@Test
	public void testgetGasStationByCarSharing() {
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStationDto gasStation2Dto = new GasStationDto(2,"GasStation2","Via Italia 2",false,false,true,true,false,
				"BlueSG",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
	
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlueSG",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
		
		String carSharing = "BlueSG";
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		gasStation2.setGasStationId(2);
		
		listGasStation.add(gasStation1);
		listGasStation.add(gasStation2);
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStationDto(gasStation2)).thenReturn(gasStation2Dto);
		
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		when(gasStationConverterMock.toGasStation(gasStation2Dto)).thenReturn(gasStation2);
		
		when(gasStationServiceImplMock.getGasStationByCarSharing(carSharing)).thenAnswer(invocation -> {
			Iterator<GasStation> iteratore;
			when(gasStationRepositoryMock.findAll()).thenAnswer(inv -> {
				Iterator<GasStation> iter = listGasStation.listIterator();
				while(iter.hasNext()) {
					GasStation gasStation = iter.next();
					if(gasStation.getGasStationId() == 1)
						listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation1));
					else
						listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation2));
				}
				return null;
			});
			switch(carSharing) {
			  case "BlueSG":
				  gasStationRepositoryMock.findAll();
				  iteratore = listGasStation.iterator();
				  while(iteratore.hasNext()) {
					  GasStation gasStation = iteratore.next();
					  if(gasStation.getCarSharing().equals(carSharing) == false) {
						  if(gasStation.getGasStationId() == 1)
							  listGasStationDto.remove(gasStationConverterMock.toGasStationDto(gasStation1));
						  else
							  listGasStationDto.remove(gasStationConverterMock.toGasStationDto(gasStation2));
					  }
				  }
			    break;
			  default:
					return null;
			}
			return null;
		});
		
		gasStationServiceImplMock.getGasStationByCarSharing(carSharing);
		
		assertEquals(1,listGasStationDto.size());
		assertEquals("BlueSG",listGasStationDto.get(0).getCarSharing());
	}
	
	@Test
	public void testsetReport() throws InvalidGasStationException, PriceException, InvalidUserException {
		UserDto user1Dto = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		User user1 = new User ("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		final double dieselPrice = 1.25;
		final double superPrice = 1.50;
		final double superPlusPrice = 1.99;
		final double gasPrice = 0.98;
		final double methanePrice = 1.01;
		final Integer gasStationId = gasStation1Dto.getGasStationId();
		final Integer userId = user1Dto.getUserId();
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		
		listGasStation.add(gasStation1);
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		
		/*when(gasStationServiceImplMock.setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId)).
		           thenAnswer( invocation -> {
		        	   
		  });*/
		
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws PriceException, InvalidGasStationException, InvalidUserException {
				
				when(gasStationRepositoryMock.findOne(gasStationId)).thenReturn(gasStation1);
				if( (gasStationRepositoryMock.findOne(gasStationId).getHasDiesel() && dieselPrice < 0) || 
	   					(gasStationRepositoryMock.findOne(gasStationId).getHasGas() && gasPrice < 0  ) || 
	   				    (gasStationRepositoryMock.findOne(gasStationId).getHasSuper() && superPrice < 0 ) ||
	   				    (gasStationRepositoryMock.findOne(gasStationId).getHasSuperPlus() &&   superPlusPrice < 0 ) || 
	   				    (gasStationRepositoryMock.findOne(gasStationId).getHasMethane() && methanePrice < 0) ) 
	   						throw new PriceException("Error! One or more of the fuel types price is negative!");
	        	   if(gasStationId < 0)
	       			throw new InvalidGasStationException("Error! the GasStationId must not be negative");
	        	   if(userId < 0)
	       			throw new InvalidUserException("Error! Invalid userId: userId can't be negative");
	        	   
	        	   double obsolence = 0; 
	        	   GasStation gasStation = gasStationRepositoryMock.findOne(gasStationId);
				   if(gasStation.getReportTimestamp() == null && gasStation.getReportDependability() == 0) {
						gasStation.setReportUser(userId);
						gasStation.setReportTimestamp(Day.calendarToString());
						
						if(gasStation.getHasDiesel())
							gasStation.setDieselPrice(dieselPrice);
						if(gasStation.getHasGas())
							gasStation.setGasPrice(gasPrice);
						if(gasStation.getHasSuper())
							gasStation.setSuperPrice(superPrice);
						if(gasStation.getHasSuperPlus())
							gasStation.setSuperPlusPrice(superPlusPrice);
						if(gasStation.getHasMethane())
							gasStation.setMethanePrice(methanePrice);
						
						try {
							obsolence = (Day.calculateDays(gasStation.getReportTimestamp()));
							if(obsolence > 7)
								obsolence = 0;
							else
								obsolence = 1 - obsolence/7;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gasStation.setReportDependability(50*(user1.getReputation()+5)/10+50*obsolence);
					}
					else {
						gasStation.setReportUser(userId);
						try {
							obsolence = (Day.calculateDays(gasStation.getReportTimestamp()));
							if(obsolence > 7)
								obsolence = 0;
							else
								obsolence = 1 - obsolence/7;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gasStation.setReportTimestamp(Day.calendarToString());
						System.out.println("ReportTimestamp: " + gasStation.getReportTimestamp());
						if(gasStation.getHasDiesel())
							gasStation.setDieselPrice(dieselPrice);
						if(gasStation.getHasGas())
							gasStation.setGasPrice(gasPrice);
						if(gasStation.getHasSuper())
							gasStation.setSuperPrice(superPrice);
						if(gasStation.getHasSuperPlus())
							gasStation.setSuperPlusPrice(superPlusPrice);
						if(gasStation.getHasMethane())
							gasStation.setMethanePrice(methanePrice);
						gasStation.setReportDependability(50*(user1.getReputation()+5)/10+50*obsolence);
					}
				   return null;
			}
		}).when(gasStationServiceImplMock).setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId);
		
	     gasStationServiceImplMock.setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId);
	     
	     assertEquals(Integer.valueOf(1),gasStation1.getReportUser());
	     assertEquals(Double.valueOf(90),Double.valueOf(gasStation1.getReportDependability()));
	}
	
	@Test(expected=InvalidGasStationException.class)
	public void testsetReportInvalidGasStationException() throws InvalidGasStationException, PriceException, InvalidUserException {
		UserDto user1Dto = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		User user1 = new User ("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		final double dieselPrice = 1.25;
		final double superPrice = 1.50;
		final double superPlusPrice = 1.99;
		final double gasPrice = 0.98;
		final double methanePrice = 1.01;
		final Integer gasStationId = -1;
		final Integer userId = user1Dto.getUserId();
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		
		listGasStation.add(gasStation1);
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		
		/*when(gasStationServiceImplMock.setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId)).
		           thenAnswer( invocation -> {
		        	   
		  });*/
		
		doAnswer(new Answer<Void>() {
			@SuppressWarnings("unused")
			public Void answer(InvocationOnMock invocation) throws PriceException, InvalidGasStationException, InvalidUserException {
				
				when(gasStationRepositoryMock.findOne(gasStationId)).thenReturn(gasStation1);
				if( (gasStationRepositoryMock.findOne(gasStationId).getHasDiesel() && dieselPrice < 0) || 
	   					(gasStationRepositoryMock.findOne(gasStationId).getHasGas() && gasPrice < 0  ) || 
	   				    (gasStationRepositoryMock.findOne(gasStationId).getHasSuper() && superPrice < 0 ) ||
	   				    (gasStationRepositoryMock.findOne(gasStationId).getHasSuperPlus() &&   superPlusPrice < 0 ) || 
	   				    (gasStationRepositoryMock.findOne(gasStationId).getHasMethane() && methanePrice < 0) ) 
	   						throw new PriceException("Error! One or more of the fuel types price is negative!");
	        	   if(gasStationId < 0)
	       			throw new InvalidGasStationException("Error! the GasStationId must not be negative");
	        	   if(userId < 0)
	       			throw new InvalidUserException("Error! Invalid userId: userId can't be negative");
	        	   
	        	   double obsolence = 0; 
	        	   GasStation gasStation = gasStationRepositoryMock.findOne(gasStationId);
				   if(gasStation.getReportTimestamp() == null && gasStation.getReportDependability() == 0) {
						gasStation.setReportUser(userId);
						gasStation.setReportTimestamp(Day.calendarToString());
						
						if(gasStation.getHasDiesel())
							gasStation.setDieselPrice(dieselPrice);
						if(gasStation.getHasGas())
							gasStation.setGasPrice(gasPrice);
						if(gasStation.getHasSuper())
							gasStation.setSuperPrice(superPrice);
						if(gasStation.getHasSuperPlus())
							gasStation.setSuperPlusPrice(superPlusPrice);
						if(gasStation.getHasMethane())
							gasStation.setMethanePrice(methanePrice);
						
						try {
							obsolence = (Day.calculateDays(gasStation.getReportTimestamp()));
							if(obsolence > 7)
								obsolence = 0;
							else
								obsolence = 1 - obsolence/7;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gasStation.setReportDependability(50*(user1.getReputation()+5)/10+50*obsolence);
					}
					else {
						gasStation.setReportUser(userId);
						try {
							obsolence = (Day.calculateDays(gasStation.getReportTimestamp()));
							if(obsolence > 7)
								obsolence = 0;
							else
								obsolence = 1 - obsolence/7;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gasStation.setReportTimestamp(Day.calendarToString());
						System.out.println("ReportTimestamp: " + gasStation.getReportTimestamp());
						if(gasStation.getHasDiesel())
							gasStation.setDieselPrice(dieselPrice);
						if(gasStation.getHasGas())
							gasStation.setGasPrice(gasPrice);
						if(gasStation.getHasSuper())
							gasStation.setSuperPrice(superPrice);
						if(gasStation.getHasSuperPlus())
							gasStation.setSuperPlusPrice(superPlusPrice);
						if(gasStation.getHasMethane())
							gasStation.setMethanePrice(methanePrice);
						gasStation.setReportDependability(50*(user1.getReputation()+5)/10+50*obsolence);
					}
				   return null;
			}
		}).when(gasStationServiceImplMock).setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId);
		
	     gasStationServiceImplMock.setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId);
	}
	
	@Test(expected=InvalidUserException.class)
	public void testsetReportInvalidUserException() throws InvalidGasStationException, PriceException, InvalidUserException {
		@SuppressWarnings("unused")
		UserDto user1Dto = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		User user1 = new User ("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		final double dieselPrice = 1.25;
		final double superPrice = 1.50;
		final double superPlusPrice = 1.99;
		final double gasPrice = 0.98;
		final double methanePrice = 1.01;
		final Integer gasStationId = gasStation1Dto.getGasStationId();
		final Integer userId = -1;
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		
		listGasStation.add(gasStation1);
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		
		/*when(gasStationServiceImplMock.setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId)).
		           thenAnswer( invocation -> {
		        	   
		  });*/
		
		doAnswer(new Answer<Void>() {
			@SuppressWarnings("unused")
			public Void answer(InvocationOnMock invocation) throws PriceException, InvalidGasStationException, InvalidUserException {
				
				when(gasStationRepositoryMock.findOne(gasStationId)).thenReturn(gasStation1);
				if( (gasStationRepositoryMock.findOne(gasStationId).getHasDiesel() && dieselPrice < 0) || 
	   					(gasStationRepositoryMock.findOne(gasStationId).getHasGas() && gasPrice < 0  ) || 
	   				    (gasStationRepositoryMock.findOne(gasStationId).getHasSuper() && superPrice < 0 ) ||
	   				    (gasStationRepositoryMock.findOne(gasStationId).getHasSuperPlus() &&   superPlusPrice < 0 ) || 
	   				    (gasStationRepositoryMock.findOne(gasStationId).getHasMethane() && methanePrice < 0) ) 
	   						throw new PriceException("Error! One or more of the fuel types price is negative!");
	        	   if(gasStationId < 0)
	       			throw new InvalidGasStationException("Error! the GasStationId must not be negative");
	        	   if(userId < 0)
	       			throw new InvalidUserException("Error! Invalid userId: userId can't be negative");
	        	   
	        	   double obsolence = 0; 
	        	   GasStation gasStation = gasStationRepositoryMock.findOne(gasStationId);
				   if(gasStation.getReportTimestamp() == null && gasStation.getReportDependability() == 0) {
						gasStation.setReportUser(userId);
						gasStation.setReportTimestamp(Day.calendarToString());
						
						if(gasStation.getHasDiesel())
							gasStation.setDieselPrice(dieselPrice);
						if(gasStation.getHasGas())
							gasStation.setGasPrice(gasPrice);
						if(gasStation.getHasSuper())
							gasStation.setSuperPrice(superPrice);
						if(gasStation.getHasSuperPlus())
							gasStation.setSuperPlusPrice(superPlusPrice);
						if(gasStation.getHasMethane())
							gasStation.setMethanePrice(methanePrice);
						
						try {
							obsolence = (Day.calculateDays(gasStation.getReportTimestamp()));
							if(obsolence > 7)
								obsolence = 0;
							else
								obsolence = 1 - obsolence/7;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gasStation.setReportDependability(50*(user1.getReputation()+5)/10+50*obsolence);
					}
					else {
						gasStation.setReportUser(userId);
						try {
							obsolence = (Day.calculateDays(gasStation.getReportTimestamp()));
							if(obsolence > 7)
								obsolence = 0;
							else
								obsolence = 1 - obsolence/7;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gasStation.setReportTimestamp(Day.calendarToString());
						System.out.println("ReportTimestamp: " + gasStation.getReportTimestamp());
						if(gasStation.getHasDiesel())
							gasStation.setDieselPrice(dieselPrice);
						if(gasStation.getHasGas())
							gasStation.setGasPrice(gasPrice);
						if(gasStation.getHasSuper())
							gasStation.setSuperPrice(superPrice);
						if(gasStation.getHasSuperPlus())
							gasStation.setSuperPlusPrice(superPlusPrice);
						if(gasStation.getHasMethane())
							gasStation.setMethanePrice(methanePrice);
						gasStation.setReportDependability(50*(user1.getReputation()+5)/10+50*obsolence);
					}
				   return null;
			}
		}).when(gasStationServiceImplMock).setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId);
		
	     gasStationServiceImplMock.setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId);
	}
	
	@Test(expected = PriceException.class)
	public void testsetReportPriceException() throws InvalidGasStationException, PriceException, InvalidUserException {
		UserDto user1Dto = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		User user1 = new User ("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		final double dieselPrice = -1.25;
		final double superPrice = -1.50;
		final double superPlusPrice = 1.99;
		final double gasPrice = 0.98;
		final double methanePrice = 1.01;
		final Integer gasStationId = gasStation1Dto.getGasStationId();
		final Integer userId = user1Dto.getUserId();
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		
		listGasStation.add(gasStation1);
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		
		/*when(gasStationServiceImplMock.setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId)).
		           thenAnswer( invocation -> {
		        	   
		  });*/
		
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws PriceException, InvalidGasStationException, InvalidUserException {
				
				when(gasStationRepositoryMock.findOne(gasStationId)).thenReturn(gasStation1);
	        	   if( (gasStationRepositoryMock.findOne(gasStationId).getHasDiesel() && dieselPrice < 0) || 
		   					(gasStationRepositoryMock.findOne(gasStationId).getHasGas() && gasPrice < 0  ) || 
		   				    (gasStationRepositoryMock.findOne(gasStationId).getHasSuper() && superPrice < 0 ) ||
		   				    (gasStationRepositoryMock.findOne(gasStationId).getHasSuperPlus() &&   superPlusPrice < 0 ) || 
		   				    (gasStationRepositoryMock.findOne(gasStationId).getHasMethane() && methanePrice < 0) ) 
		   						throw new PriceException("Error! One or more of the fuel types price is negative!");
	        	   if(gasStationId < 0)
	       			throw new InvalidGasStationException("Error! the GasStationId must not be negative");
	        	   if(userId < 0)
	       			throw new InvalidUserException("Error! Invalid userId: userId can't be negative");
	        	   
	        	   double obsolence = 0; 
	        	   GasStation gasStation = gasStationRepositoryMock.findOne(gasStationId);
				   if(gasStation.getReportTimestamp() == null && gasStation.getReportDependability() == 0) {
						gasStation.setReportUser(userId);
						gasStation.setReportTimestamp(Day.calendarToString());
						
						if(gasStation.getHasDiesel())
							gasStation.setDieselPrice(dieselPrice);
						if(gasStation.getHasGas())
							gasStation.setGasPrice(gasPrice);
						if(gasStation.getHasSuper())
							gasStation.setSuperPrice(superPrice);
						if(gasStation.getHasSuperPlus())
							gasStation.setSuperPlusPrice(superPlusPrice);
						if(gasStation.getHasMethane())
							gasStation.setMethanePrice(methanePrice);
						
						try {
							obsolence = (Day.calculateDays(gasStation.getReportTimestamp()));
							if(obsolence > 7)
								obsolence = 0;
							else
								obsolence = 1 - obsolence/7;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gasStation.setReportDependability(50*(user1.getReputation()+5)/10+50*obsolence);
					}
					else {
						gasStation.setReportUser(userId);
						try {
							obsolence = (Day.calculateDays(gasStation.getReportTimestamp()));
							if(obsolence > 7)
								obsolence = 0;
							else
								obsolence = 1 - obsolence/7;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gasStation.setReportTimestamp(Day.calendarToString());
						System.out.println("ReportTimestamp: " + gasStation.getReportTimestamp());
						if(gasStation.getHasDiesel())
							gasStation.setDieselPrice(dieselPrice);
						if(gasStation.getHasGas())
							gasStation.setGasPrice(gasPrice);
						if(gasStation.getHasSuper())
							gasStation.setSuperPrice(superPrice);
						if(gasStation.getHasSuperPlus())
							gasStation.setSuperPlusPrice(superPlusPrice);
						if(gasStation.getHasMethane())
							gasStation.setMethanePrice(methanePrice);
						gasStation.setReportDependability(50*(user1.getReputation()+5)/10+50*obsolence);
					}
				   return null;
			}
		}).when(gasStationServiceImplMock).setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId);
		
	     gasStationServiceImplMock.setReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testgetGasStationsByProximity() throws GPSDataException {
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStationDto gasStation2Dto = new GasStationDto(2,"GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
	
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
		
		final double lat = 80.761;
		final double lon = 154.987;
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		gasStation2.setGasStationId(2);
		
		listGasStation.add(gasStation1);
		listGasStation.add(gasStation2);
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		
		when(gasStationConverterMock.toGasStationDto(gasStation2)).thenReturn(gasStation2Dto);
		when(gasStationConverterMock.toGasStation(gasStation2Dto)).thenReturn(gasStation2);
		
		when(gasStationServiceImplMock.getAllGasStations()).then( invocation -> {
			Iterator<GasStation> iter = listGasStation.listIterator();
			while(iter.hasNext()) {
				GasStation gasStation = iter.next();
				if(gasStation.getGasStationId() == 1)
					listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation1));
				else
					listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation2));
			}
			return listGasStationDto;
		});
		
		when(gasStationServiceImplMock.getGasStationsByProximity(lat, lon)).thenAnswer( invocation -> {
			if((lat < -90 || lat >= 90) || (lon < -180 || lon >= 180))
				throw new GPSDataException("coordinates out of bounds");
			gasStationServiceImplMock.getAllGasStations();
			listGasStationDto.stream()
			.filter( (g) -> Haversine.distance(lat, lon, g.getLat(), g.getLon() ) <= 1.0)
			.sorted( (g1,g2) -> Double.compare(Haversine.distance(lat, lon, g1.getLat(), g1.getLon() ), Haversine.distance(lat, lon, g2.getLat(), g2.getLon() ) ) )
			.collect(Collectors.toList());
			return null;
		});
		
		gasStationServiceImplMock.getGasStationsByProximity(lat, lon);
		
		assertEquals(2,listGasStationDto.size());
	}
	
	@SuppressWarnings("unused")
	@Test(expected=GPSDataException.class)
	public void testgetGasStationsByProximityThrowException() throws GPSDataException {
		GasStationDto gasStation1Dto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStationDto gasStation2Dto = new GasStationDto(2,"GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
	
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",61.649,117.550,0,0,1.25,1.55,0,null,null,0);
		
		final double lat = 180.761;
		final double lon = 154.987;
		
		listGasStationDto.clear();
		listGasStation.clear();
		
		gasStation1.setGasStationId(1);
		gasStation2.setGasStationId(2);
		
		listGasStation.add(gasStation1);
		listGasStation.add(gasStation2);
		
		when(gasStationConverterMock.toGasStationDto(gasStation1)).thenReturn(gasStation1Dto);
		when(gasStationConverterMock.toGasStation(gasStation1Dto)).thenReturn(gasStation1);
		
		when(gasStationConverterMock.toGasStationDto(gasStation2)).thenReturn(gasStation2Dto);
		when(gasStationConverterMock.toGasStation(gasStation2Dto)).thenReturn(gasStation2);
		
		when(gasStationServiceImplMock.getAllGasStations()).then( invocation -> {
			Iterator<GasStation> iter = listGasStation.listIterator();
			while(iter.hasNext()) {
				GasStation gasStation = iter.next();
				if(gasStation.getGasStationId() == 1)
					listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation1));
				else
					listGasStationDto.add(gasStationConverterMock.toGasStationDto(gasStation2));
			}
			return listGasStationDto;
		});
		
		when(gasStationServiceImplMock.getGasStationsByProximity(lat, lon)).thenAnswer( invocation -> {
			if((lat < -90 || lat >= 90) || (lon < -180 || lon >= 180))
				throw new GPSDataException("coordinates out of bounds");
			gasStationServiceImplMock.getAllGasStations();
			listGasStationDto.stream()
			.filter( (g) -> Haversine.distance(lat, lon, g.getLat(), g.getLon() ) <= 1.0)
			.sorted( (g1,g2) -> Double.compare(Haversine.distance(lat, lon, g1.getLat(), g1.getLon() ), Haversine.distance(lat, lon, g2.getLat(), g2.getLon() ) ) )
			.collect(Collectors.toList());
			return null;
		});
		
		gasStationServiceImplMock.getGasStationsByProximity(lat, lon);
	}
	
}
