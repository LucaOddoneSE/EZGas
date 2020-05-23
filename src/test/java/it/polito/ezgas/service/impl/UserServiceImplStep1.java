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
}