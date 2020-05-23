package it.polito.ezgas.service;

import java.util.List;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;

public interface UserServiceStep2 {

	UserDto getUserById(Integer userId) throws InvalidUserException ;
	
	UserDto saveUser(UserDto userDto) ;
	
	List<UserDto> getAllUsers() ;

	Boolean deleteUser(Integer userId) throws InvalidUserException ;
	
	LoginDto login(IdPw credentials) throws InvalidLoginDataException ;

	Integer increaseUserReputation(Integer userId) throws InvalidUserException ;

	Integer decreaseUserReputation(Integer userId) throws InvalidUserException ;

}
