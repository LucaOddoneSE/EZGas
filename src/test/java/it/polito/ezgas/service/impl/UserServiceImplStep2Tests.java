package it.polito.ezgas.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserServiceImplStep2Tests {

	private UserServiceimpl userServiceImp = mock(UserServiceimpl.class);

	private UserConverter userConverter = mock(UserConverter.class);
	
	private List<User> listUsers = new ArrayList<>();
	private List<UserDto> listUsersDto = new ArrayList<>();
	private List<Integer> ids = new ArrayList<>();
	
	// Empty DB
	@Test
	public void testgetAllUsersEmptyDB() {
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.getAllUsers()).thenReturn(listUsersDto);
		assertEquals(0,userServiceImp.getAllUsers().size());
	}

	// Throw Exception(userId<0)
	@Test(expected = InvalidUserException.class)
	public void testgetUserByIdNegativeUserId() throws InvalidUserException {
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.getUserById(-5)).thenThrow(new InvalidUserException("Error UserId < 0") );
		userServiceImp.getUserById(-5);
	}
	
	//NoResults
	@Test
	public void testgetUserByIdNoUsersYet() throws InvalidUserException {
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.getUserById(1)).thenAnswer(invocation -> {
			if(ids.contains(1))
				return listUsersDto.get(0);
			else
				return null;
		});
		
		when(userServiceImp.getUserById(2)).thenAnswer(invocation -> {
			if(ids.contains(2))
				return listUsersDto.get(1);
			else
				return null;
		});
		
		assertNull(userServiceImp.getUserById(1));
		assertNull(userServiceImp.getUserById(2));
	}
}