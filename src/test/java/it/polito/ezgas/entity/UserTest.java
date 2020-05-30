package it.polito.ezgas.entity;

import org.junit.Test;

public class UserTest {
	
	@Test
    public void testUserId() {
		User user = new User();
		int UserId = Integer.MAX_VALUE+1;
		user.setUserId(UserId);
		assert(user.getUserId() == UserId);
    }
	
	@Test
    public void testUserId1() {
		User user = new User();
		int UserId = Integer.MIN_VALUE+1;
		user.setUserId(UserId);
		assert(user.getUserId() == UserId);
    }
	
    public void testUserId2() {
		User user = new User();
		int UserId = 4;
		user.setUserId(UserId);
		assert(user.getUserId() == UserId);
    }
    
    public void testUserId3() {
		User user = new User();
		int UserId = -4;
		user.setUserId(UserId);
		assert(user.getUserId() == UserId);
    }
    
    
	@Test
    public void testPassword() {
		User user = new User();
		String Pass = "testpass";
		user.setPassword(Pass);
		assert(user.getPassword() == Pass);
    }
	
	@Test
    public void testPassword1() {
		User user = new User();
		String Pass = "";
		user.setPassword(Pass);
		assert(user.getPassword() == Pass);
    }
    
    
	@Test
    public void testEmail() {
		User user = new User();
		String Email = "s274475@studenti.polito.it";
		user.setEmail(Email);
		assert(user.getEmail() == Email);
    }
	
    
	@Test
    public void testEmail1() {
		User user = new User();
		String Email = "";
		user.setEmail(Email);
		assert(user.getEmail() == Email);
    }
	
		
	@Test
    public void testReputation1() {
		User user = new User();
		Integer reputation = 5+1;
		user.setReputation(reputation);
		assert(user.getReputation() == reputation);
    }
	
	@Test
    public void testReputation2() {
		User user = new User();
		Integer reputation = -5-1;
		user.setReputation(reputation);
		assert(user.getReputation() == reputation);
    }
	
	@Test
    public void testReputation3() {
		User user = new User();
		Integer reputation = 1;
		user.setReputation(reputation);
		assert(user.getReputation() == reputation);
    }
	
	@Test
    public void testReputation4() {
		User user = new User();
		Integer reputation = -1;
		user.setReputation(reputation);
		assert(user.getReputation() == reputation);
    }
}
