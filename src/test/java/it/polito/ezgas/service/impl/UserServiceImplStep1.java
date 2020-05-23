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
	
	
}