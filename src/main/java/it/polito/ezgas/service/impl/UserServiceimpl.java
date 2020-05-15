package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	private UserConverter userConverter = new UserConverter();

	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		if( userId < 0 ) throw new InvalidUserException("Error! Invalid userId: userId can't be negative");
		if(userRepository.findOne(userId) == null)
			return null;
		System.out.println("Found User with userId submitted");
		System.out.println(userRepository.findOne(userId).getUserId().toString() + " " +
				           userRepository.findOne(userId).getUserName().toString() + " " +
				           userRepository.findOne(userId).getPassword().toString() + " " +
				           userRepository.findOne(userId).getEmail().toString() + " " +
				           userRepository.findOne(userId).getReputation().toString());
		return userConverter.toUserDto(userRepository.findOne(userId));
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		if(userDto == null) {
			System.out.println("Error! you want to save in the database a null object User");
			return null;
		}
		userRepository.save(userConverter.toUser(userDto));
		System.out.println("User Correctly saved!");
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		User user;
		Iterator<User> iter;
		List<UserDto> listUsersDto = new ArrayList<>();
		if(userRepository.findAll().size() == 0)
			return listUsersDto;
		iter = userRepository.findAll().iterator();
		while(iter.hasNext()) {
			listUsersDto.add(userConverter.toUserDto(iter.next()));
		}
		System.out.println("List of all Users:");
		iter = userRepository.findAll().iterator();
		while(iter.hasNext()) {
			user = iter.next();
			System.out.println( user.getUserId().toString() + " " +
					            user.getUserName().toString() + " " +
					            user.getPassword().toString() + " " +
					            user.getEmail().toString() + " " +
					            user.getReputation().toString() );
		}
		return listUsersDto;
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		if(userId < 0) throw new InvalidUserException("Error! Invalid userId: userId can't be negative\n");
		if( userRepository.exists(userId) ) {
			userRepository.delete(userId);
			System.out.println("User successfully deleted!");
			return true;
		}
		return false;
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		int counter = 0;
		String email;
		String password;
		User user;
		Iterator<User> iter;
		LoginDto loginDto = null;
		if(credentials == null)
			throw new InvalidLoginDataException("Error! Passed null credentials to login() method");
		if(credentials.getUser() == null || credentials.getPw() == null)
			throw new InvalidLoginDataException("Error! Passed null credentials to login() method");
		email = credentials.getUser();
		password = credentials.getPw();
		if( userRepository.findAll().size() != 0) {
			iter = userRepository.findAll().iterator();
			while( iter.hasNext() ) {
				user = iter.next();
				if(user.getEmail().equals(email) && user.getPassword().equals(password))
					break;
				counter++;
			}
			if(counter == userRepository.findAll().size())
				throw new InvalidLoginDataException("Error! Such user does not exist!");
			iter = userRepository.findAll().iterator();
			while( counter != 0 ) {
				iter.next();
				counter--;
			}
			user = iter.next();
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
				System.out.println("Found User! Going to logIn!");
				loginDto = new LoginDto(user.getUserId(),user.getUserName(),"token",user.getEmail(),user.getReputation());
				if(user.getAdmin())
					loginDto.setAdmin(true);
			}
		}
		else
			throw new InvalidLoginDataException("Error! No one User still exists");
		return loginDto;
	}

	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
