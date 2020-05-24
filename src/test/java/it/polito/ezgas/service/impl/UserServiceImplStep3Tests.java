package it.polito.ezgas.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

public class UserServiceImplStep3Tests {

	private UserServiceimpl userServiceImp = mock(UserServiceimpl.class);
	private UserConverter userConverter = mock(UserConverter.class);
	private UserRepository userRepository = mock(UserRepository.class);
	
	private List<User> listUsers = new ArrayList<>();
	private List<UserDto> listUsersDto = new ArrayList<>();
	private List<Integer> ids = new ArrayList<>();
	
	// Empty DB
	@Test
	public void testgetAllUsersEmptyDB() {
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.getAllUsers()).thenAnswer( invocation -> {
			Iterator<User> iter;
			when(userRepository.findAll()).thenReturn(listUsers);
			iter = userRepository.findAll().iterator();
			while(iter.hasNext()) {
				listUsersDto.add(userConverter.toUserDto(iter.next()));
			}
			return listUsersDto;
		});
		
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
			when(userRepository.exists(1)).thenReturn(ids.contains(1));
			when(userConverter.toUserDto(userRepository.findOne(1)))
					.thenAnswer( invocazione -> {
						Iterator<User> iter = listUsers.iterator();
						while(iter.hasNext()) {
							User user = iter.next();
							if(user.getUserId() == 1)
								return new UserDto(user.getUserId(),user.getUserName(),
										user.getPassword(),user.getEmail(),user.getReputation());
						}
						return null;
					});
			if(userRepository.exists(1))
				return userConverter.toUserDto(userRepository.findOne(1));
			else
				return null;
		});
		
		when(userServiceImp.getUserById(2)).thenAnswer(invocation -> {
			when(userRepository.exists(2)).thenReturn(ids.contains(2));
			when(userConverter.toUserDto(userRepository.findOne(2)))
					.thenAnswer( invocazione -> {
						Iterator<User> iter = listUsers.iterator();
						while(iter.hasNext()) {
							User user = iter.next();
							if(user.getUserId() == 2)
								return new UserDto(user.getUserId(),user.getUserName(),
										user.getPassword(),user.getEmail(),user.getReputation());
						}
						return null;
					});
			if(userRepository.exists(2))
				return userConverter.toUserDto(userRepository.findOne(2));
			else
				return null;
		});
		
		assertNull(userServiceImp.getUserById(1));
		assertNull(userServiceImp.getUserById(2));
	}
}	