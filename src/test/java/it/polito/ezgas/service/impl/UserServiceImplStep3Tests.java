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

	// Throw Exception(userId<0)-m
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

        //Save Users
	@Test
	public void testsaveUserNewUsers() throws InvalidUserException {
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.saveUser(user1)).thenAnswer( invocation -> {
			when(userRepository.exists(1)).thenReturn(ids.contains(1));
			when(userConverter.toUser(user1)).thenReturn(new User(user1.getUserName(), user1.getPassword(),user1.getEmail(),user1.getReputation()));
			if(userRepository.exists(1)) {
				User user = null;
				Iterator<User> iter = listUsers.iterator();
				while(iter.hasNext()) {
					user = iter.next();
					if(user.getUserId() == 1)
						break;
				}
				user.setUserId(user1.getUserId());
				user.setUserName(user1.getUserName());
				user.setEmail(user1.getEmail());
				user.setPassword(user1.getPassword());
				user.setReputation(user1.getReputation());
				return new UserDto(user.getUserId(),user.getUserName(),user.getPassword(),user.getEmail(),user.getReputation());
				
			}
			User entity1 = userConverter.toUser(user1);
			entity1.setUserId(user1.getUserId());
			listUsers.add(entity1);
			ids.add(1);
			return user1;
		});
		
		when(userServiceImp.saveUser(user2)).thenAnswer( invocation -> {
			when(userRepository.exists(2)).thenReturn(ids.contains(2));
			when(userConverter.toUser(user2)).thenReturn(new User(user2.getUserName(), user2.getPassword(),user2.getEmail(),user2.getReputation()));
			if(userRepository.exists(2)) {
				User user = null;
				Iterator<User> iter = listUsers.iterator();
				while(iter.hasNext()) {
					user = iter.next();
					if(user.getUserId() == 2)
						break;
				}
				user.setUserId(user1.getUserId());
				user.setUserName(user1.getUserName());
				user.setEmail(user1.getEmail());
				user.setPassword(user1.getPassword());
				user.setReputation(user1.getReputation());
				return new UserDto(user.getUserId(),user.getUserName(),user.getPassword(),user.getEmail(),user.getReputation());
				
			}
			User entity2 = userConverter.toUser(user2);
			entity2.setUserId(user2.getUserId());
			listUsers.add(entity2);
			ids.add(2);
			return user2;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
		
		assertEquals(1,listUsers.get(0).getUserId());
		assertEquals(2,listUsers.get(1).getUserId());
	}

        //GetExistingUserById
	public void testGetUserById() throws InvalidUserException {
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
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
		
		when(userServiceImp.saveUser(user1)).thenAnswer( invocation -> {
			when(userRepository.exists(1)).thenReturn(ids.contains(1));
			when(userConverter.toUser(user1)).thenReturn(new User(user1.getUserName(), user1.getPassword(),user1.getEmail(),user1.getReputation()));
			if(userRepository.exists(1)) {
				User user = null;
				Iterator<User> iter = listUsers.iterator();
				while(iter.hasNext()) {
					user = iter.next();
					if(user.getUserId() == 1)
						break;
				}
				user.setUserId(user1.getUserId());
				user.setUserName(user1.getUserName());
				user.setEmail(user1.getEmail());
				user.setPassword(user1.getPassword());
				user.setReputation(user1.getReputation());
				return new UserDto(user.getUserId(),user.getUserName(),user.getPassword(),user.getEmail(),user.getReputation());
				
			}
			User entity1 = userConverter.toUser(user1);
			entity1.setUserId(user1.getUserId());
			listUsers.add(entity1);
			ids.add(1);
			return user1;
		});
		
		when(userServiceImp.saveUser(user2)).thenAnswer( invocation -> {
			when(userRepository.exists(2)).thenReturn(ids.contains(2));
			when(userConverter.toUser(user2)).thenReturn(new User(user2.getUserName(), user2.getPassword(),user2.getEmail(),user2.getReputation()));
			if(userRepository.exists(2)) {
				User user = null;
				Iterator<User> iter = listUsers.iterator();
				while(iter.hasNext()) {
					user = iter.next();
					if(user.getUserId() == 2)
						break;
				}
				user.setUserId(user1.getUserId());
				user.setUserName(user1.getUserName());
				user.setEmail(user1.getEmail());
				user.setPassword(user1.getPassword());
				user.setReputation(user1.getReputation());
				return new UserDto(user.getUserId(),user.getUserName(),user.getPassword(),user.getEmail(),user.getReputation());
				
			}
			User entity2 = userConverter.toUser(user2);
			entity2.setUserId(user2.getUserId());
			listUsers.add(entity2);
			ids.add(2);
			return user2;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
		
		assertEquals("lucaoddone@polito.it",userServiceImp.getUserById(1).getEmail());
		assertEquals("paolaoddone@polito.it",userServiceImp.getUserById(2).getEmail());
			
	}
}	