package it.polito.ezgas.service;

import java.util.ArrayList;
import java.util.List;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;

public interface UserServiceStepN {

	List<UserDto> listUsers = new ArrayList<>();
	List<Integer> ids = new ArrayList<>();

	default UserDto getUserById(Integer userId) throws InvalidUserException {
		if (userId < 0)
			throw new InvalidUserException("Throwing InvalidUserException");
		if (userId == 1 && ids.contains(1))
			return listUsers.get(0);
		if (userId == 2 && ids.contains(2))
			return listUsers.get(1);
		return null;
	}

	default UserDto saveUser(UserDto userDto) {
		if (userDto.getUserId() == null)
			return null;
		if (userDto.getUserId() == 1 && ids.contains(1) == false) {
			ids.add(0,1);
			listUsers.add(0, userDto);
			return userDto;
		}
		if (userDto.getUserId() == 2 && ids.contains(2) == false) {
			ids.add(1,2);
			listUsers.add(1, userDto);
			return userDto;
		}
		return null;
	}

	default List<UserDto> getAllUsers() {
		return listUsers;
	}

	default Boolean deleteUser(Integer userId) throws InvalidUserException {

		if (userId < 0)
			throw new InvalidUserException("Throwing InvalidUserException");
		if (userId == 1 && ids.contains(1)) {
			ids.remove(1);
			listUsers.remove(0);
			return true;
		}
		if (userId == 2 && ids.contains(2)) {
			ids.remove(2);
			listUsers.remove(1);
			return true;
		}
		return null;
	}

	default LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		if (credentials == null)
			throw new InvalidLoginDataException("Error! The method receives a null object");
		if (credentials.getUser() == null || credentials.getPw() == null)
			throw new InvalidLoginDataException("Error! Passed null credentials");
		if (credentials.getUser().equals("lucaoddone@polito.it") && credentials.getPw().equals("Password")
				&& ids.contains(1))
			return new LoginDto(1, "Luca Oddone", "token", "lucaoddone@polito.it", 3);
		if (credentials.getUser().equals("paolaoddone@polito.it") && credentials.getPw().equals("Password")
				&& ids.contains(2))
			return new LoginDto(2, "Paola Oddone", "token", "paolaoddone@polito.it", 4);
		return null;
	}

	default Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		if (userId < 0)
			throw new InvalidUserException("Throwing InvalidUserException");
		if (userId == 1 && ids.contains(1))
			if (getUserById(userId).getReputation() < 5) {
				getUserById(userId).setReputation(getUserById(userId).getReputation() + 1);
				return getUserById(userId).getReputation();
			}
		if (userId == 2 && ids.contains(2))
			if (getUserById(userId).getReputation() < 5) {
				getUserById(userId).setReputation(getUserById(userId).getReputation() + 1);
				return getUserById(userId).getReputation();
			}
		return null;
	}

	default Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		if (userId < 0)
			throw new InvalidUserException("Throwing InvalidUserException");
		if (userId == 1 && ids.contains(1))
			if (getUserById(userId).getReputation() > -5) {
				getUserById(userId).setReputation(getUserById(userId).getReputation() - 1);
				return getUserById(userId).getReputation();
			}
		if (userId == 2)
			if (getUserById(userId).getReputation() > -5 && ids.contains(2)) {
				getUserById(userId).setReputation(getUserById(userId).getReputation() - 1);
				return getUserById(userId).getReputation();
			}
		return null;
	}

}