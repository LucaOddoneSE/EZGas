package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.GasStation;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {

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



	@Test
	public void contextLoads() {
	}
	

}




