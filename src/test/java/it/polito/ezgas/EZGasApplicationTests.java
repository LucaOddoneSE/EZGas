package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.GasStationDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {

		
	@Test
    public void testgetGasStationName() {
		GasStationDto GasStation = new GasStationDto();
		String GasStationName = "test";
		GasStation.setGasStationName(GasStationName);
		assert(GasStation.getGasStationName() == GasStationName);
    }



	@Test
	public void contextLoads() {
	}
	

}
