package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdPwTests {

	@Test
    public void IdPw() {
		IdPw IdPass = new IdPw();
		String user = "Test";
		IdPass.setUser(user);
		assert(IdPass.getUser()==user);
    }
	


	@Test
	public void contextLoads() {
	}
	

}
