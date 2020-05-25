package it.polito.ezgas.service.impl;

import static org.junit.jupiter.api.Assertions.*;


import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.service.UserServiceStepN;

import org.junit.Test;

public class UserServiceImplStepN implements UserServiceStepN {

	private UserServiceImplStepN UserServiceImplStepN;
	
	// Empty DB
	@Test
	public void testgetAllUsersEmptyDB() {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(0, UserServiceImplStepN.getAllUsers().size());
	}
	
	// Throw Exception(userId<0)
	@Test(expected = InvalidUserException.class)
	public void testgetUserByIdNegativeUserId() throws InvalidUserException {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		UserServiceImplStepN.getUserById(-5);
	}
	
	//EmptyList
	@Test
	public void testgetUserByIdNoUsersYet() throws InvalidUserException {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertNull(UserServiceImplStepN.getUserById(1));
		assertNull(UserServiceImplStepN.getUserById(2));
	}
	
	//Save Users
	@Test
	public void testsaveUserNewUsers() {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
	}
	
	//Avoid saving Users already present in the database
	@Test
	public void testsaveUserUsersAlreadyPresent() {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertNull(UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3)));
		assertNull(UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4)));
	}
	
	//DeleteOneUser
	@Test
	public void testdeleteUser() throws InvalidUserException {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertTrue(UserServiceImplStepN.deleteUser(1));
	}
	
	// Throw Exception(userId<0)
	@Test(expected = InvalidUserException.class)
	public void testdeleteUserNegativeUserId() throws InvalidUserException {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		UserServiceImplStepN.deleteUser(-5);
	}
	
	//Retrieve All users present in the DB
	@Test
	public void testgetAllUsers() {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(2, UserServiceImplStepN.getAllUsers().size());
	}
	
	//Perform a login
	@Test
	public void testLogin() throws InvalidLoginDataException {
		IdPw credentials = new IdPw();
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		credentials.setUser("lucaoddone@polito.it");
		credentials.setPw("Password");
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(1,UserServiceImplStepN.login(credentials).getUserId());
		credentials.setUser("paolaoddone@polito.it");
		credentials.setPw("Password");
		assertEquals(2,UserServiceImplStepN.login(credentials).getUserId());
	}
	
	//Passing null credentials
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNullCredentials() throws InvalidLoginDataException {
		IdPw credentials = null;
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		UserServiceImplStepN.login(credentials);
	}
	
	//Passing null e-mail credential
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNullEmail() throws InvalidLoginDataException {
		IdPw credentials = new IdPw();
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		credentials.setUser(null);
		credentials.setPw("Password");
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		UserServiceImplStepN.login(credentials);
	}
	
	//Passing null password credential
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNullPassword() throws InvalidLoginDataException {
		IdPw credentials = new IdPw();
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		credentials.setUser("lucaoddone@polito.it");
		credentials.setPw(null);
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		UserServiceImplStepN.login(credentials);
	}
	
	//Passing both e-mail and password null
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNull() throws InvalidLoginDataException {
		IdPw credentials = new IdPw();
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		credentials.setUser(null);
		credentials.setPw(null);
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		UserServiceImplStepN.login(credentials);
	}
	
	//Increasing User reputation
	@Test
	public void testIncreaseUserReputationExistingUsers() throws InvalidUserException {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(4,UserServiceImplStepN.increaseUserReputation(1));
		assertEquals(5,UserServiceImplStepN.increaseUserReputation(2));
	}
	
	//Throw Exception(userId<0)
	@Test(expected=InvalidUserException.class)
	public void testIncreaseUserReputationNegativeUserId() throws InvalidUserException {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(4,UserServiceImplStepN.increaseUserReputation(-6));
	}
	
	//Already reached the max reputation
	@Test
	public void testIncreaseUserReputationReachingMax() throws InvalidUserException {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(4,UserServiceImplStepN.increaseUserReputation(1));
		assertEquals(5,UserServiceImplStepN.increaseUserReputation(1));
		assertEquals(5,UserServiceImplStepN.increaseUserReputation(2));
		
		assertNull(UserServiceImplStepN.increaseUserReputation(1));
		assertNull(UserServiceImplStepN.increaseUserReputation(2));
	}
	
	//Decreasing User reputation
	@Test
	public void testDecreaseUserReputationExistingUsers() throws InvalidUserException {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(2,UserServiceImplStepN.decreaseUserReputation(1));
		assertEquals(3,UserServiceImplStepN.decreaseUserReputation(2));
	}
	
	//Throw Exception(userId<0)
	@Test(expected=InvalidUserException.class)
	public void testDecreaseUserReputationNegativeUserId() throws InvalidUserException {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4))
				.getUserId());
		assertEquals(2,UserServiceImplStepN.decreaseUserReputation(-6));
	}
	
	//Already reached the min reputation
	@Test
	public void testDecreaseUserReputationReachingMin() throws InvalidUserException {
		UserServiceImplStepN = new UserServiceImplStepN();
		UserServiceStepN.ids.clear();
		UserServiceStepN.listUsers.clear();
		assertEquals(1, UserServiceImplStepN
				.saveUser(new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", -3))
				.getUserId());
		assertEquals(2, UserServiceImplStepN
				.saveUser(new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", -4))
				.getUserId());
		assertEquals(-4,UserServiceImplStepN.decreaseUserReputation(1));
		assertEquals(-5,UserServiceImplStepN.decreaseUserReputation(1));
		assertEquals(-5,UserServiceImplStepN.decreaseUserReputation(2));
		
		assertNull(UserServiceImplStepN.decreaseUserReputation(1));
		assertNull(UserServiceImplStepN.decreaseUserReputation(2));
	}
}