package it.polito.ezgas.converter;

import org.springframework.stereotype.Component;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

@Component
public class UserConverter {
	
	private User user;
	private UserDto userDto;
	
	public UserConverter() {
		
	}
	
	public UserDto toUserDto(User userSource) {
		userDto = new UserDto();
		userDto.setUserId(userSource.getUserId());
		userDto.setUserName(userSource.getUserName());
		userDto.setPassword(userSource.getPassword());
		userDto.setEmail(userSource.getEmail());
		userDto.setReputation(userSource.getReputation());
		userDto.setAdmin(userSource.getAdmin());
		
		return userDto;
	}
	
	public User toUser(UserDto userDtoSource) {
		user = new User();
		user.setUserId(userDtoSource.getUserId());
		user.setUserName(userDtoSource.getUserName());
		user.setPassword(userDtoSource.getPassword());
		user.setEmail(userDtoSource.getEmail());
		user.setReputation(userDtoSource.getReputation());
		user.setAdmin(userDtoSource.getAdmin());
		
		return user;
	}
	
}