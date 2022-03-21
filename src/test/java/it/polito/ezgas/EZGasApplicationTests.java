package it.polito.ezgas;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {
	
	@Mock
	private Connection connectionMock = mock(Connection.class);
	@Mock
	private UserRepository userRepositoryMock = mock(UserRepository.class);
	
	@Test(expected=SQLException.class)
	public void testSetupDbWithData() throws SQLException {
		Connection connectionMock = mock(Connection.class);
		UserRepository userRepositoryMock = mock(UserRepository.class);
		User admin = new User("admin","admin","admin@polito.it",5);
		User userMock = mock(User.class);
		List<User> listUsers = new ArrayList<>();
		
		when(DriverManager.getConnection(anyString(),anyString(),anyString())).thenReturn(connectionMock).thenThrow(new SQLException("Connection to the database failed"));
		DriverManager.getConnection("URL", "User", "Password");
		assertEquals(null,connectionMock);
		
		when(userRepositoryMock.findAll()).thenReturn(listUsers).thenReturn(listUsers = Arrays.asList(admin));
		userRepositoryMock.findAll();
		assertEquals(0,listUsers.size());
		when(userRepositoryMock.save(userMock)).thenReturn(userMock = admin);
		userRepositoryMock.save(userMock);
		assertEquals("admin",userMock.getUserName());
		assertEquals("admin",userMock.getPassword());
		assertEquals("admin@polito.it",userMock.getEmail());
		assertEquals((Integer) 5,userMock.getReputation());
		
		userRepositoryMock.findAll();
		assertEquals(1,listUsers.size());
		
		DriverManager.getConnection("URL", "User", "Password");
		
	}
	
}
