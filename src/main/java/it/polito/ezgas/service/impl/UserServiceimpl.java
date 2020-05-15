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
		userRepository.save(userConverter.toUser(userDto));
		System.out.println("User Correctly saved!");
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		Iterator<User> iter;
		List<User> listUsers = new ArrayList<>();
		List<UserDto> listUsersDto = new ArrayList<>();
		if(userRepository.findAll().size() == 0)
			return listUsersDto;
		userRepository.findAll().forEach(listUsers::add);
		iter = listUsers.iterator();
		while(iter.hasNext()) {
			listUsersDto.add(userConverter.toUserDto(iter.next()));
		}
		System.out.println("List of all Users:");
		iter = listUsers.iterator();
		while(iter.hasNext()) {
			
			System.out.println( iter.next().getUserId().toString() + " " +
					            iter.next().getUserName().toString() + " " +
					            iter.next().getPassword().toString() + " " +
					            iter.next().getEmail().toString() + " " +
					            iter.next().getReputation().toString() );
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

		String email;
		LoginDto loginDto = null;
		if(credentials == null)
			throw new InvalidLoginDataException("Error! Passed null credentials to login() method");
		email = credentials.getUser();
		if( userRepository.findAll().size() != 0) {
			for(User u : userRepository.findAll() ) {
				if(u.getEmail().equals(email)) {
					System.out.println("User found");
					if(credentials.getPw().equals(u.getPassword())) {
						System.out.println("PW match, Going to logIn!");
						loginDto = new LoginDto(u.getUserId(),u.getUserName(),"token",u.getEmail(),u.getReputation());
						loginDto.setAdmin(u.getAdmin());
					}else {
						System.out.println("PW doesnt match match");
					}
				}
			}
			
		}
		else
			throw new InvalidLoginDataException("Error! No User exists yet");
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
