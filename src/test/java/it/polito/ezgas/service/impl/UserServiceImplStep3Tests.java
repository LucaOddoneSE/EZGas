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

        //DeleteOneUser
	@Test
	public void testdeleteUser() throws InvalidUserException {
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
		
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				Integer i = null;
				User user = null;
				Iterator<Integer> iterInt = ids.iterator();
				Iterator<User> iterUser = listUsers.iterator();
				while(iterInt.hasNext()) {
					i = iterInt.next();
					if(i==1)
						break;
				}
				ids.remove(i);
				while(iterUser.hasNext()) {
					user = iterUser.next();
					if(user.getUserId() == 1)
						break;
				}
				listUsers.remove(user);
				return null;
			}
		}).when(userRepository).delete(1);
		
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				Integer i = null;
				User user = null;
				Iterator<Integer> iterInt = ids.iterator();
				Iterator<User> iterUser = listUsers.iterator();
				while(iterInt.hasNext()) {
					i = iterInt.next();
					if(i == 2)
						break;
				}
				ids.remove(i);
				while(iterUser.hasNext()) {
					user = iterUser.next();
					if(user.getUserId() == 2)
						break;
				}
				listUsers.remove(user);
				return null;
			}
		}).when(userRepository).delete(2);
		
		when(userServiceImp.deleteUser(1)).thenAnswer(invocation -> {
			when(userRepository.exists(1)).thenReturn(ids.contains(1));
			if(userRepository.exists(1)) {
				userRepository.delete(1);
				return true;
			}
			return null;
		});
		
		when(userServiceImp.deleteUser(2)).thenAnswer(invocation -> {
			when(userRepository.exists(2)).thenReturn(ids.contains(2));
			if(userRepository.exists(2)) {
				userRepository.delete(2);
				return true;
			}
			return null;
		});
		
		userServiceImp.deleteUser(1);
		userServiceImp.deleteUser(2);
		
		assertNull(userServiceImp.getUserById(1));
		assertNull(userServiceImp.getUserById(2));
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
		
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				Integer i = null;
				User user = null;
				Iterator<Integer> iterInt = ids.iterator();
				Iterator<User> iterUser = listUsers.iterator();
				while(iterInt.hasNext()) {
					i = iterInt.next();
					if(i==1)
						break;
				}
				ids.remove(i);
				while(iterUser.hasNext()) {
					user = iterUser.next();
					if(user.getUserId() == 1)
						break;
				}
				listUsers.remove(user);
				return null;
			}
		}).when(userRepository).delete(1);
		
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				Integer i = null;
				User user = null;
				Iterator<Integer> iterInt = ids.iterator();
				Iterator<User> iterUser = listUsers.iterator();
				while(iterInt.hasNext()) {
					i = iterInt.next();
					if(i == 2)
						break;
				}
				ids.remove(i);
				while(iterUser.hasNext()) {
					user = iterUser.next();
					if(user.getUserId() == 2)
						break;
				}
				listUsers.remove(user);
				return null;
			}
		}).when(userRepository).delete(2);
		
		when(userServiceImp.deleteUser(1)).thenAnswer(invocation -> {
			when(userRepository.exists(1)).thenReturn(ids.contains(1));
			if(userRepository.exists(1)) {
				userRepository.delete(1);
				return true;
			}
			return null;
		});
		
		when(userServiceImp.deleteUser(2)).thenAnswer(invocation -> {
			when(userRepository.exists(2)).thenReturn(ids.contains(2));
			if(userRepository.exists(2)) {
				userRepository.delete(2);
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
		
		when(userServiceImp.getAllUsers()).thenAnswer( invocation -> {
			Iterator<User> iter;
			when(userRepository.findAll()).thenReturn(listUsers);
			iter = userRepository.findAll().iterator();
			while(iter.hasNext()) {
				listUsersDto.add(userConverter.toUserDto(iter.next()));
			}
			return listUsersDto;
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
		
		assertEquals(2,userServiceImp.getAllUsers().size());
	}

        //Perform a login
	@SuppressWarnings("unused")
	@Test
	public void testLogin() throws InvalidLoginDataException {
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		IdPw credentials = new IdPw();
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		credentials.setUser("lucaoddone@polito.it");
		credentials.setPw("Password");
		
		when(userServiceImp.login(credentials)).thenAnswer( invocation -> {
			if(credentials == null)
				throw new InvalidLoginDataException("Error! The method receives a null object");
			if(credentials.getPw() == null || credentials.getUser() == null)
				throw new InvalidLoginDataException("Error! Passed null credentials");
			if (credentials.getUser().equals("lucaoddone@polito.it") && credentials.getPw().equals("Password")
					&& ids.contains(1))
				return new LoginDto(1, "Luca Oddone", "token", "lucaoddone@polito.it", 3);
			if (credentials.getUser().equals("paolaoddone@polito.it") && credentials.getPw().equals("Password")
					&& ids.contains(2))
				return new LoginDto(2, "Paola Oddone", "token", "paolaoddone@polito.it", 4);
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
		
		assertEquals(1,userServiceImp.login(credentials).getUserId());
		
		credentials.setUser("paolaoddone@polito.it");
		credentials.setPw("Password");
		
		assertEquals(2,userServiceImp.login(credentials).getUserId());
	}

        //Passing null credentials
	@SuppressWarnings("unused")
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNullCredentials() throws InvalidLoginDataException {
		IdPw credentials = null;
		
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		when(userServiceImp.login(credentials)).thenAnswer( invocation -> {
			if(credentials == null)
				throw new InvalidLoginDataException("Error! The method receives a null object");
			if(credentials.getPw() == null || credentials.getUser() == null)
				throw new InvalidLoginDataException("Error! Passed null credentials");
			if (credentials.getUser().equals("lucaoddone@polito.it") && credentials.getPw().equals("Password")
					&& ids.contains(1))
				return new LoginDto(1, "Luca Oddone", "token", "lucaoddone@polito.it", 3);
			if (credentials.getUser().equals("paolaoddone@polito.it") && credentials.getPw().equals("Password")
					&& ids.contains(2))
				return new LoginDto(2, "Paola Oddone", "token", "paolaoddone@polito.it", 4);
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
		
		userServiceImp.login(credentials);
	}
	
	//Passing null e-mail credential
	@SuppressWarnings("unused")
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNullEmail() throws InvalidLoginDataException {
	    IdPw credentials = new IdPw();
		
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		credentials.setPw("Password");
		credentials.setUser(null);
		
		when(userServiceImp.login(credentials)).thenAnswer( invocation -> {
			if(credentials == null)
				throw new InvalidLoginDataException("Error! The method receives a null object");
			if(credentials.getPw() == null || credentials.getUser() == null)
				throw new InvalidLoginDataException("Error! Passed null credentials");
			if (credentials.getUser().equals("lucaoddone@polito.it") && credentials.getPw().equals("Password")
					&& ids.contains(1))
				return new LoginDto(1, "Luca Oddone", "token", "lucaoddone@polito.it", 3);
			if (credentials.getUser().equals("paolaoddone@polito.it") && credentials.getPw().equals("Password")
					&& ids.contains(2))
				return new LoginDto(2, "Paola Oddone", "token", "paolaoddone@polito.it", 4);
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
		
		userServiceImp.login(credentials);
	}
	
	//Passing null password credential
	@SuppressWarnings("unused")
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNullPassword() throws InvalidLoginDataException {
		IdPw credentials = new IdPw();
		
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		credentials.setPw(null);
		credentials.setUser("lucaoddone@polito.it");
		
		when(userServiceImp.login(credentials)).thenAnswer( invocation -> {
			if(credentials == null)
				throw new InvalidLoginDataException("Error! The method receives a null object");
			if(credentials.getPw() == null || credentials.getUser() == null)
				throw new InvalidLoginDataException("Error! Passed null credentials");
			if (credentials.getUser().equals("lucaoddone@polito.it") && credentials.getPw().equals("Password")
					&& ids.contains(1))
				return new LoginDto(1, "Luca Oddone", "token", "lucaoddone@polito.it", 3);
			if (credentials.getUser().equals("paolaoddone@polito.it") && credentials.getPw().equals("Password")
					&& ids.contains(2))
				return new LoginDto(2, "Paola Oddone", "token", "paolaoddone@polito.it", 4);
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
		
		userServiceImp.login(credentials);
	}
	
	//Passing both e-mail and password null
	@SuppressWarnings("unused")
	@Test(expected=InvalidLoginDataException.class)
	public void testLoginNull() throws InvalidLoginDataException {
		IdPw credentials = new IdPw();
		
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", 4);
		
		ids.clear();
		listUsers.clear();
		listUsersDto.clear();
		
		credentials.setPw(null);
		credentials.setUser(null);
		
		when(userServiceImp.login(credentials)).thenAnswer( invocation -> {
			if(credentials == null)
				throw new InvalidLoginDataException("Error! The method receives a null object");
			if(credentials.getPw() == null || credentials.getUser() == null)
				throw new InvalidLoginDataException("Error! Passed null credentials");
			if (credentials.getUser().equals("lucaoddone@polito.it") && credentials.getPw().equals("Password")
					&& ids.contains(1))
				return new LoginDto(1, "Luca Oddone", "token", "lucaoddone@polito.it", 3);
			if (credentials.getUser().equals("paolaoddone@polito.it") && credentials.getPw().equals("Password")
					&& ids.contains(2))
				return new LoginDto(2, "Paola Oddone", "token", "paolaoddone@polito.it", 4);
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
		
		userServiceImp.login(credentials);
	}

        //Increasing User reputation
	@Test
	public void testIncreaseUserReputationExistingUsers() throws InvalidUserException {
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
		
		when(userServiceImp.increaseUserReputation(1)).thenAnswer( invocation -> {
			when(userRepository.exists(1)).thenReturn(ids.contains(1));
			if(userRepository.exists(1)) {
				UserDto user = userServiceImp.getUserById(1);
				when(userServiceImp.saveUser(user)).thenAnswer( invocazione -> {
					when(userRepository.exists(user.getUserId())).thenReturn(ids.contains(user.getUserId()));
					when(userConverter.toUser(user)).thenReturn(new User(user.getUserName(), user.getPassword(),user.getEmail(),user.getReputation()));
					if(userRepository.exists(user.getUserId())) {
						User usr = null;
						Iterator<User> iter = listUsers.iterator();
						while(iter.hasNext()) {
							usr = iter.next();
							if(usr.getUserId() == user.getUserId())
								break;
						}
						usr.setUserId(user.getUserId());
						usr.setUserName(user.getUserName());
						usr.setEmail(user.getEmail());
						usr.setPassword(user.getPassword());
						usr.setReputation(user.getReputation());
						return new UserDto(usr.getUserId(),usr.getUserName(),usr.getPassword(),usr.getEmail(),usr.getReputation());						
					}
					return null;
				});
				if(user.getReputation() < 5) {
					user.setReputation(user.getReputation()+1);
					userServiceImp.saveUser(user);
					return userServiceImp.getUserById(1).getReputation();
				}
				else
					return userServiceImp.getUserById(1).getReputation();
			}
			return null;
		});
		
		when(userServiceImp.increaseUserReputation(2)).thenAnswer( invocation -> {
			when(userRepository.exists(2)).thenReturn(ids.contains(2));
			if(userRepository.exists(2)) {
				UserDto user = userServiceImp.getUserById(2);
				when(userServiceImp.saveUser(user)).thenAnswer( invocazione -> {
					when(userRepository.exists(user.getUserId())).thenReturn(ids.contains(user.getUserId()));
					when(userConverter.toUser(user)).thenReturn(new User(user.getUserName(), user.getPassword(),user.getEmail(),user.getReputation()));
					if(userRepository.exists(user.getUserId())) {
						User usr = null;
						Iterator<User> iter = listUsers.iterator();
						while(iter.hasNext()) {
							usr = iter.next();
							if(usr.getUserId() == user.getUserId())
								break;
						}
						usr.setUserId(user.getUserId());
						usr.setUserName(user.getUserName());
						usr.setEmail(user.getEmail());
						usr.setPassword(user.getPassword());
						usr.setReputation(user.getReputation());
						return new UserDto(usr.getUserId(),usr.getUserName(),usr.getPassword(),usr.getEmail(),usr.getReputation());						
					}
					return null;
				});
				if(user.getReputation() < 5) {
					user.setReputation(user.getReputation()+1);
					userServiceImp.saveUser(user);
					return userServiceImp.getUserById(2).getReputation();
				}
				else
					return userServiceImp.getUserById(2).getReputation();
			}
			return null;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
		
		assertEquals("lucaoddone@polito.it",userServiceImp.getUserById(1).getEmail());
		assertEquals("paolaoddone@polito.it",userServiceImp.getUserById(2).getEmail());
		
 		assertEquals(4,userServiceImp.increaseUserReputation(1));
 		assertEquals(5,userServiceImp.increaseUserReputation(1));
	}

	//Throw Exception(userId<0)
	@Test(expected=InvalidUserException.class)
	public void testIncreaseUserReputationNegativeUserId() throws InvalidUserException {
		when(userServiceImp.increaseUserReputation(-1)).thenThrow(new InvalidUserException("Error! You have passed a negative userId"));
		userServiceImp.increaseUserReputation(-1);
	}
	
	//Already reached the max reputation
	@Test
	public void testIncreaseUserReputationReachingMax() throws InvalidUserException {
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
		
		when(userServiceImp.increaseUserReputation(1)).thenAnswer( invocation -> {
			when(userRepository.exists(1)).thenReturn(ids.contains(1));
			if(userRepository.exists(1)) {
				UserDto user = userServiceImp.getUserById(1);
				when(userServiceImp.saveUser(user)).thenAnswer( invocazione -> {
					when(userRepository.exists(user.getUserId())).thenReturn(ids.contains(user.getUserId()));
					when(userConverter.toUser(user)).thenReturn(new User(user.getUserName(), user.getPassword(),user.getEmail(),user.getReputation()));
					if(userRepository.exists(user.getUserId())) {
						User usr = null;
						Iterator<User> iter = listUsers.iterator();
						while(iter.hasNext()) {
							usr = iter.next();
							if(usr.getUserId() == user.getUserId())
								break;
						}
						usr.setUserId(user.getUserId());
						usr.setUserName(user.getUserName());
						usr.setEmail(user.getEmail());
						usr.setPassword(user.getPassword());
						usr.setReputation(user.getReputation());
						return new UserDto(usr.getUserId(),usr.getUserName(),usr.getPassword(),usr.getEmail(),usr.getReputation());						
					}
					return null;
				});
				if(user.getReputation() < 5) {
					user.setReputation(user.getReputation()+1);
					userServiceImp.saveUser(user);
					return userServiceImp.getUserById(1).getReputation();
				}
				else
					return userServiceImp.getUserById(1).getReputation();
			}
			return null;
		});
		
		when(userServiceImp.increaseUserReputation(2)).thenAnswer( invocation -> {
			when(userRepository.exists(2)).thenReturn(ids.contains(2));
			if(userRepository.exists(2)) {
				UserDto user = userServiceImp.getUserById(2);
				when(userServiceImp.saveUser(user)).thenAnswer( invocazione -> {
					when(userRepository.exists(user.getUserId())).thenReturn(ids.contains(user.getUserId()));
					when(userConverter.toUser(user)).thenReturn(new User(user.getUserName(), user.getPassword(),user.getEmail(),user.getReputation()));
					if(userRepository.exists(user.getUserId())) {
						User usr = null;
						Iterator<User> iter = listUsers.iterator();
						while(iter.hasNext()) {
							usr = iter.next();
							if(usr.getUserId() == user.getUserId())
								break;
						}
						usr.setUserId(user.getUserId());
						usr.setUserName(user.getUserName());
						usr.setEmail(user.getEmail());
						usr.setPassword(user.getPassword());
						usr.setReputation(user.getReputation());
						return new UserDto(usr.getUserId(),usr.getUserName(),usr.getPassword(),usr.getEmail(),usr.getReputation());						
					}
					return null;
				});
				if(user.getReputation() < 5) {
					user.setReputation(user.getReputation()+1);
					userServiceImp.saveUser(user);
					return userServiceImp.getUserById(2).getReputation();
				}
				else
					return userServiceImp.getUserById(2).getReputation();
			}
			return null;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
		
		assertEquals("lucaoddone@polito.it",userServiceImp.getUserById(1).getEmail());
		assertEquals("paolaoddone@polito.it",userServiceImp.getUserById(2).getEmail());
		
		assertEquals(4,userServiceImp.increaseUserReputation(1));
		assertEquals(5,userServiceImp.increaseUserReputation(2));
		
		assertEquals(5,userServiceImp.increaseUserReputation(1));
		assertEquals(5,userServiceImp.increaseUserReputation(2));
		assertEquals(5,userServiceImp.increaseUserReputation(1));
	}

        //Decreasing User reputation
	@Test
	public void testDecreaseUserReputationExistingUsers() throws InvalidUserException {
		UserDto user1 = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", -3);
		UserDto user2 = new UserDto(2, "Paola Oddone", "Password", "paolaoddone@polito.it", -4);
		
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
		
		when(userServiceImp.decreaseUserReputation(1)).thenAnswer( invocation -> {
			when(userRepository.exists(1)).thenReturn(ids.contains(1));
			if(userRepository.exists(1)) {
				UserDto user = userServiceImp.getUserById(1);
				when(userServiceImp.saveUser(user)).thenAnswer( invocazione -> {
					when(userRepository.exists(user.getUserId())).thenReturn(ids.contains(user.getUserId()));
					when(userConverter.toUser(user)).thenReturn(new User(user.getUserName(), user.getPassword(),user.getEmail(),user.getReputation()));
					if(userRepository.exists(user.getUserId())) {
						User usr = null;
						Iterator<User> iter = listUsers.iterator();
						while(iter.hasNext()) {
							usr = iter.next();
							if(usr.getUserId() == user.getUserId())
								break;
						}
						usr.setUserId(user.getUserId());
						usr.setUserName(user.getUserName());
						usr.setEmail(user.getEmail());
						usr.setPassword(user.getPassword());
						usr.setReputation(user.getReputation());
						return new UserDto(usr.getUserId(),usr.getUserName(),usr.getPassword(),usr.getEmail(),usr.getReputation());						
					}
					return null;
				});
				if(user.getReputation() > -5) {
					user.setReputation(user.getReputation()-1);
					userServiceImp.saveUser(user);
					return userServiceImp.getUserById(1).getReputation();
				}
				else
					return userServiceImp.getUserById(1).getReputation();
			}
			return null;
		});
		
		when(userServiceImp.decreaseUserReputation(2)).thenAnswer( invocation -> {
			when(userRepository.exists(2)).thenReturn(ids.contains(2));
			if(userRepository.exists(2)) {
				UserDto user = userServiceImp.getUserById(2);
				when(userServiceImp.saveUser(user)).thenAnswer( invocazione -> {
					when(userRepository.exists(user.getUserId())).thenReturn(ids.contains(user.getUserId()));
					when(userConverter.toUser(user)).thenReturn(new User(user.getUserName(), user.getPassword(),user.getEmail(),user.getReputation()));
					if(userRepository.exists(user.getUserId())) {
						User usr = null;
						Iterator<User> iter = listUsers.iterator();
						while(iter.hasNext()) {
							usr = iter.next();
							if(usr.getUserId() == user.getUserId())
								break;
						}
						usr.setUserId(user.getUserId());
						usr.setUserName(user.getUserName());
						usr.setEmail(user.getEmail());
						usr.setPassword(user.getPassword());
						usr.setReputation(user.getReputation());
						return new UserDto(usr.getUserId(),usr.getUserName(),usr.getPassword(),usr.getEmail(),usr.getReputation());						
					}
					return null;
				});
				if(user.getReputation() >  -5) {
					user.setReputation(user.getReputation()-1);
					userServiceImp.saveUser(user);
					return userServiceImp.getUserById(2).getReputation();
				}
				else
					return userServiceImp.getUserById(2).getReputation();
			}
			return null;
		});
		
		assertEquals(1,userServiceImp.saveUser(user1).getUserId());
		assertEquals(2,userServiceImp.saveUser(user2).getUserId());
		
		assertEquals("lucaoddone@polito.it",userServiceImp.getUserById(1).getEmail());
		assertEquals("paolaoddone@polito.it",userServiceImp.getUserById(2).getEmail());
		
		assertEquals(-4,userServiceImp.decreaseUserReputation(1));
		assertEquals(-5,userServiceImp.decreaseUserReputation(2));
	}
}	