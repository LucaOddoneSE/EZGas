package it.polito.ezgas.service.impl;

import static org.junit.jupiter.api.Assertions.*;


import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.service.UserServiceStep1;

import org.junit.Test;

public class UserServiceImplStep1 implements UserServiceStep1 {

	private UserServiceImplStep1 userServiceImplStep1;
	
	// Empty DB
	@Test
	public void testgetAllUsersEmptyDB() {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(0, userServiceImplStep1.getAllUsers().size());
	}
	
	// Throw Exception(userId<0)
	@Test(expected = InvalidUserException.class)
	public void testgetUserByIdNegativeUserId() throws InvalidUserException {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		userServiceImplStep1.getUserById(-5);
	}
	
	//EmptyList
	@Test
	public void testgetUserByIdNoUsersYet() throws InvalidUserException {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertNull(userServiceImplStep1.getUserById(1));
		assertNull(userServiceImplStep1.getUserById(2));
	}
	
	//Save Users
	@Test
	public void testsaveUserNewUsers() {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
	}
	
	//Avoid saving Users already present in the database
	@Test
	public void testsaveUserUsersAlreadyPresent() {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertNull(userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3)));
		assertNull(userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4)));
	}
	
	//DeleteOneUser
	@Test
	public void testdeleteUser() throws InvalidUserException {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertTrue(userServiceImplStep1.deleteUser(1));
	}
	
	// Throw Exception(userId<0)
	@Test(expected = InvalidUserException.class)
	public void testdeleteUserNegativeUserId() throws InvalidUserException {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		userServiceImplStep1.deleteUser(-5);
	}
	
	//Retrieve All users present in the DB
	@Test
	public void testgetAllUsers() {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(2, userServiceImplStep1.getAllUsers().size());
	}
	
	//Perform a login
	@Test
	public void testLogin() throws InvalidLoginDataException {
		IdPw credentials = new IdPw();
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		credentials.setUser("lucaoddone@polito.it");
		credentials.setPw("Password");
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(1,userServiceImplStep1.login(credentials).getUserId());
		credentials.setUser("paolaoddone@polito.it");
		credentials.setPw("Password");
		assertEquals(2,userServiceImplStep1.login(credentials).getUserId());
	}
	
	//Passing null credentials
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNullCredentials() throws InvalidLoginDataException {
		IdPw credentials = null;
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		userServiceImplStep1.login(credentials);
	}
	
	//Passing null e-mail credential
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNullEmail() throws InvalidLoginDataException {
		IdPw credentials = new IdPw();
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		credentials.setUser(null);
		credentials.setPw("Password");
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		userServiceImplStep1.login(credentials);
	}
	
	//Passing null password credential
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNullPassword() throws InvalidLoginDataException {
		IdPw credentials = new IdPw();
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		credentials.setUser("lucaoddone@polito.it");
		credentials.setPw(null);
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		userServiceImplStep1.login(credentials);
	}
	
	//Passing both e-mail and password null
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNull() throws InvalidLoginDataException {
		IdPw credentials = new IdPw();
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		credentials.setUser(null);
		credentials.setPw(null);
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		userServiceImplStep1.login(credentials);
	}
	
	//Increasing User reputation
	@Test
	public void testIncreaseUserReputationExistingUsers() throws InvalidUserException {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(4,userServiceImplStep1.increaseUserReputation(1));
		assertEquals(5,userServiceImplStep1.increaseUserReputation(2));
	}
	
	//Throw Exception(userId<0)
	@Test(expected=InvalidUserException.class)
	public void testIncreaseUserReputationNegativeUserId() throws InvalidUserException {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(4,userServiceImplStep1.increaseUserReputation(-6));
	}
	
	//Already reached the max reputation
	@Test
	public void testIncreaseUserReputationReachingMax() throws InvalidUserException {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(4,userServiceImplStep1.increaseUserReputation(1));
		assertEquals(5,userServiceImplStep1.increaseUserReputation(1));
		assertEquals(5,userServiceImplStep1.increaseUserReputation(2));
		
		assertNull(userServiceImplStep1.increaseUserReputation(1));
		assertNull(userServiceImplStep1.increaseUserReputation(2));
	}
	
	//Decreasing User reputation
	@Test
	public void testDecreaseUserReputationExistingUsers() throws InvalidUserException {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(2,userServiceImplStep1.decreaseUserReputation(1));
		assertEquals(3,userServiceImplStep1.decreaseUserReputation(2));
	}
	
	//Throw Exception(userId<0)
	@Test(expected=InvalidUserException.class)
	public void testDecreaseUserReputationNegativeUserId() throws InvalidUserException {
		userServiceImplStep1 = new UserServiceImplStep1();
		UserServiceStep1.ids.clear();
		UserServiceStep1.listUsers.clear();
		assertEquals(1, userServiceImplStep1
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, userServiceImplStep1
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(2,userServiceImplStep1.decreaseUserReputation(-6));
	}
}