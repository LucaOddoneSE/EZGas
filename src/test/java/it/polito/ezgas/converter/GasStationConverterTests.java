package it.polito.ezgas.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

public class GasStationConverterTests {
	private GasStation gasStation;
	private GasStationDto gasStationDto;
	private GasStationConverter gasStationConverter = new GasStationConverter();
	
	@Test
	public void testToGasStationDto() {
		gasStation = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		
		gasStation.setGasStationId(1);
		
		gasStationDto = gasStationConverter.toGasStationDto(gasStation);
		
		assertEquals(gasStation.getGasStationId(),gasStationDto.getGasStationId());
		assertEquals(gasStation.getGasStationName(),gasStationDto.getGasStationName());
		assertEquals(gasStation.getGasStationAddress(),gasStationDto.getGasStationAddress());
		assertEquals(gasStation.getCarSharing(),gasStationDto.getCarSharing());
	}
	
	@Test
	public void testToGasStation() {
		gasStationDto = new GasStationDto(1,"GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",81.574,111.320,1.25,1.55,0,0,0.90,null,null,0);
		
		gasStation = gasStationConverter.toGasStation(gasStationDto);
		
		assertEquals(gasStation.getGasStationId(),gasStationDto.getGasStationId());
		assertEquals(gasStation.getGasStationName(),gasStationDto.getGasStationName());
		assertEquals(gasStation.getGasStationAddress(),gasStationDto.getGasStationAddress());
		assertEquals(gasStation.getCarSharing(),gasStationDto.getCarSharing());
	}
}
