package it.polito.ezgas.service.impl;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import it.polito.ezgas.service.impl.*;

public class UserServiceimplTest {

	UserServiceimplTest GU = new UserServiceimplTest();

	@Test
	public void testGetUserById() {
		AssertEquals("can't be Negative", GU.GetUserById(-1));
        AssertEquals("can't be Null", GU.GetUserById());
		
	}



	@Test
	public void testSaveUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreaseUserReputation() {
		fail("Not yet implemented");
	}

	@Test
	public void testDecreaseUserReputation() {
		fail("Not yet implemented");
	}

}
