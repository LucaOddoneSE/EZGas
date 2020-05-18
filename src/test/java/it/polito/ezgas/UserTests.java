package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

	@Test
    public void testUserId() {
		User user = new User();
		int UserId = 20;
		user.setUserId(UserId);
		assert(user.getUserId() == UserId);
    }
	
	@Test
    public void testUserName() {
		User user = new User();
		String UserName = "test";
		user.setUserName(UserName);
		assert(user.getUserName() == UserName);
    }
	
	@Test
    public void testPassword() {
		User user = new User();
		String Pass = "test!@";
		user.setPassword(Pass);
		assert(user.getUserName() == Pass);
    }

	@Test
	public void contextLoads() {
	}
	

}
