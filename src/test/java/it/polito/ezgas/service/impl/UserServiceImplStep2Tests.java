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
	
	//Save Users
	@Test
	public void testsaveUserNewUsers() {
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.saveUser(user1)).thenAnswer( invocation -> {
			if(ids.contains(1)==true)
				return null;
			User entity1 = new User("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
			entity1.setUserId(1);
			when(userConverter.toUser(user1)).thenReturn(entity1);
			listUsers.add(0,userConverter.toUser(user1));
			listUsersDto.add(0,user1);
			ids.add(0,1);
			return user1;
		});
		
		when(userServiceImp.saveUser(user2)).thenAnswer( invocation -> {
			if(ids.contains(2)==true)
				return null;
			User entity2 = new User("Paola Oddone", "Password", "paolaoddone@polito.it", 4);
			entity2.setUserId(2);
			when(userConverter.toUser(user2)).thenReturn(entity2);
			listUsers.add(1,userConverter.toUser(user2));
			listUsersDto.add(1,user2);
			ids.add(1,2);
			return user2;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
	}
	
	//Avoid saving Users already present in the database
	@Test
	public void testsaveUserUsersAlreadyPresent() {
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.saveUser(user1)).thenAnswer( invocation -> {
			if(ids.contains(1)==true)
				return null;
			User entity1 = new User("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
			entity1.setUserId(1);
			when(userConverter.toUser(user1)).thenReturn(entity1);
			listUsers.add(0,userConverter.toUser(user1));
			listUsersDto.add(0,user1);
			ids.add(0,1);
			return user1;
		});
		
		when(userServiceImp.saveUser(user2)).thenAnswer( invocation -> {
			if(ids.contains(2)==true)
				return null;
			User entity2 = new User("Paola Oddone", "Password", "paolaoddone@polito.it", 4);
			entity2.setUserId(2);
			when(userConverter.toUser(user2)).thenReturn(entity2);
			listUsers.add(1,userConverter.toUser(user2));
			listUsersDto.add(1,user2);
			ids.add(1,2);
			return user2;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
		
		assertNull(userServiceImp.saveUser(user1));
		assertNull(userServiceImp.saveUser(user2));
	}
	
	//GetExistingUserById
	public void testGetUserById() throws InvalidUserException {
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.getUserById(1)).thenAnswer(invocation -> {
			if(ids.contains(1))
				return listUsersDto.get(0);
			return null;
		});
		
		when(userServiceImp.getUserById(2)).thenAnswer(invocation -> {
			if(ids.contains(2))
				return listUsersDto.get(1);
			return null;
		});
		
		when(userServiceImp.saveUser(user1)).thenAnswer( invocation -> {
			if(ids.contains(1)==true)
				return null;
			User entity1 = new User("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
			entity1.setUserId(1);
			when(userConverter.toUser(user1)).thenReturn(entity1);
			listUsers.add(0,userConverter.toUser(user1));
			listUsersDto.add(0,user1);
			ids.add(0,1);
			return user1;
		});
		
		when(userServiceImp.saveUser(user2)).thenAnswer( invocation -> {
			if(ids.contains(2)==true)
				return null;
			User entity2 = new User("Paola Oddone", "Password", "paolaoddone@polito.it", 4);
			entity2.setUserId(2);
			when(userConverter.toUser(user2)).thenReturn(entity2);
			listUsers.add(1,userConverter.toUser(user2));
			listUsersDto.add(1,user2);
			ids.add(1,2);
			return user2;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
		
		assertEquals("lucaoddone@polito.it",userServiceImp.getUserById(1).getEmail());
		assertEquals("paolaoddone@polito.it",userServiceImp.getUserById(2).getEmail());
			
	}

	//DeleteOneUser
	@Test
	public void testdeleteUser() throws InvalidUserException {
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.saveUser(user1)).thenAnswer( invocation -> {
			if(ids.contains(1)==true)
				return null;
			User entity1 = new User("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
			entity1.setUserId(1);
			when(userConverter.toUser(user1)).thenReturn(entity1);
			listUsers.add(0,userConverter.toUser(user1));
			listUsersDto.add(0,user1);
			ids.add(0,1);
			return user1;
		});
		
		when(userServiceImp.saveUser(user2)).thenAnswer( invocation -> {
			if(ids.contains(2)==true)
				return null;
			User entity2 = new User("Paola Oddone", "Password", "paolaoddone@polito.it", 4);
			entity2.setUserId(2);
			when(userConverter.toUser(user2)).thenReturn(entity2);
			listUsers.add(1,userConverter.toUser(user2));
			listUsersDto.add(1,user2);
			ids.add(1,2);
			return user2;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
		
		when(userServiceImp.deleteUser(1)).thenAnswer(invocation -> {
			if(ids.contains(1)) {
				ids.remove(0);
				listUsers.remove(0);
				listUsersDto.remove(0);
				return true;
			}
			return null;
		});
		
		when(userServiceImp.deleteUser(2)).thenAnswer(invocation -> {
			if(ids.contains(2) && ids.contains(1)) {
				ids.remove(1);
				listUsers.remove(1);
				listUsersDto.remove(1);
				return true;
			}
			if(ids.contains(2) && ids.contains(1)==false) {
				ids.remove(0);
				listUsers.remove(0);
				listUsersDto.remove(0);
				return true;
			}
			return null;
		});
		
		assertTrue(userServiceImp.deleteUser(1));
		assertTrue(userServiceImp.deleteUser(2));
	}
	
	//Delete a User that does not exist
	@Test
	public void testdeleteUserDoesNotExist() throws InvalidUserException {
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.saveUser(user1)).thenAnswer( invocation -> {
			if(ids.contains(1)==true)
				return null;
			User entity1 = new User("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
			entity1.setUserId(1);
			when(userConverter.toUser(user1)).thenReturn(entity1);
			listUsers.add(0,userConverter.toUser(user1));
			listUsersDto.add(0,user1);
			ids.add(0,1);
			return user1;
		});
		
		when(userServiceImp.saveUser(user2)).thenAnswer( invocation -> {
			if(ids.contains(2)==true)
				return null;
			User entity2 = new User("Paola Oddone", "Password", "paolaoddone@polito.it", 4);
			entity2.setUserId(2);
			when(userConverter.toUser(user2)).thenReturn(entity2);
			listUsers.add(1,userConverter.toUser(user2));
			listUsersDto.add(1,user2);
			ids.add(1,2);
			return user2;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
		
		when(userServiceImp.deleteUser(1)).thenAnswer(invocation -> {
			if(ids.contains(1)) {
				ids.remove(0);
				listUsers.remove(0);
				listUsersDto.remove(0);
				return true;
			}
			return null;
		});
		
		when(userServiceImp.deleteUser(2)).thenAnswer(invocation -> {
			if(ids.contains(2) && ids.contains(1)) {
				ids.remove(1);
				listUsers.remove(1);
				listUsersDto.remove(1);
				return true;
			}
			if(ids.contains(2) && ids.contains(1)==false) {
				ids.remove(0);
				listUsers.remove(0);
				listUsersDto.remove(0);
				return true;
			}
			return null;
		});
		
		assertTrue(userServiceImp.deleteUser(1));
		assertTrue(userServiceImp.deleteUser(2));
		
		assertNull(userServiceImp.deleteUser(1));
		assertNull(userServiceImp.deleteUser(2));
	}
	
	// Throw Exception(userId<0)
	@Test(expected = InvalidUserException.class)
	public void testdeleteUserNegativeUserId() throws InvalidUserException {
		when(userServiceImp.deleteUser(-100)).thenThrow(new InvalidUserException("Error! UserId < 0"));
		userServiceImp.deleteUser(-100);
	}
	
	//Retrieve All users present in the DB
	@Test
	public void testgetAllUsers() {
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.getAllUsers()).thenReturn(listUsersDto);
		
		when(userServiceImp.saveUser(user1)).thenAnswer( invocation -> {
			if(ids.contains(1)==true)
				return null;
			User entity1 = new User("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
			entity1.setUserId(1);
			when(userConverter.toUser(user1)).thenReturn(entity1);
			listUsers.add(0,userConverter.toUser(user1));
			listUsersDto.add(0,user1);
			ids.add(0,1);
			return user1;
		});
		
		when(userServiceImp.saveUser(user2)).thenAnswer( invocation -> {
			if(ids.contains(2)==true)
				return null;
			User entity2 = new User("Paola Oddone", "Password", "paolaoddone@polito.it", 4);
			entity2.setUserId(2);
			when(userConverter.toUser(user2)).thenReturn(entity2);
			listUsers.add(1,userConverter.toUser(user2));
			listUsersDto.add(1,user2);
			ids.add(1,2);
			return user2;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
		
		assertEquals(2,userServiceImp.getAllUsers().size());
	}
		
}