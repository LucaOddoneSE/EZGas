package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.service.impl.UserServiceimplTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {
	
	public class UserServiceimplTest {
		
		@Test
		public void testGetUserById() {
			UserServiceimplTest GU = new UserServiceimplTest();
			AssertTrue(userDto,GU.GetUserById(-1));
		}

	}
	
	@Test
	public void contextLoads() {
	}
	

}
