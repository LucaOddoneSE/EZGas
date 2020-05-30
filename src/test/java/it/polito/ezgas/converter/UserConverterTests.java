package it.polito.ezgas.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverterTests {

	
	private User user;
	
	private UserDto userDto;
	
	private UserConverter userConverter = new UserConverter();
	
	@Test
	public void testToUserDto() {
		userDto = new UserDto(1, "Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		
		user = userConverter.toUser(userDto);
		
		assertEquals(1,user.getUserId());
		assertEquals("Luca Oddone",user.getUserName());
		assertEquals("Password",user.getPassword());
		assertEquals("lucaoddone@polito.it",user.getEmail());
		assertEquals(3,user.getReputation());
	}
	
	@Test
	public void testToUser() {
		user = new User("Paola Oddone","Password","paolaoddone@polito.it",4);
		user.setUserId(2);
		
		userDto = userConverter.toUserDto(user);
		
		assertEquals(2,userDto.getUserId());
		assertEquals("Paola Oddone",userDto.getUserName());
		assertEquals("Password",userDto.getPassword());
		assertEquals("paolaoddone@polito.it",userDto.getEmail());
		assertEquals(4,userDto.getReputation());
	}

}
