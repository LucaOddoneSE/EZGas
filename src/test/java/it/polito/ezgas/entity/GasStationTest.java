package it.polito.ezgas.entity;

import org.junit.jupiter.api.Test;

public class GasStationTest {
	
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
}
