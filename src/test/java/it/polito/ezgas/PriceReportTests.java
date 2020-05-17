package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.PriceReport;
import it.polito.ezgas.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceReportTests {

	@Test
    public void testUser() {
		PriceReport PriceReport = new PriceReport(null, 0, 0, 0, 0);
		User user = null;
		PriceReport.setUser(user);
		assert(PriceReport.getUser() == user);
    }
	
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

	

	@Test
	public void contextLoads() {
	}
	

}
