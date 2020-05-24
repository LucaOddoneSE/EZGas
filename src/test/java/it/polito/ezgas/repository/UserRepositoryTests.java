package it.polito.ezgas.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import it.polito.ezgas.entity.User;

public class UserRepositoryTests implements JpaRepository<User, Integer> {
	
	private List<User> listUsers = new ArrayList<>();
	
	@Test
	public void testExists() {
		User user1 = new User("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		User user2 = new User("Paola Oddone","Password","paolaoddone@polito.it",4);
		
		listUsers.clear();
		user1.setUserId(1);
		user2.setUserId(2);
		listUsers.add(user1);
		listUsers.add(user2);
		
		assertTrue(exists(1));
		assertTrue(exists(2));
		assertFalse(exists(100));
	}
	
	@Test
	public void testDelete() {
		User user1 = new User("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		User user2 = new User("Paola Oddone","Password","paolaoddone@polito.it",4);
		
		listUsers.clear();
		user1.setUserId(1);
		user2.setUserId(2);
		listUsers.add(user1);
		listUsers.add(user2);
		
		delete(1);
		assertEquals(1,listUsers.size());
		delete(2);
		assertEquals(0,listUsers.size());
	}
	
	@Test
	public void testFindAll() {
		User user1 = new User("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		User user2 = new User("Paola Oddone","Password","paolaoddone@polito.it",4);
		
		listUsers.clear();
		user1.setUserId(1);
		user2.setUserId(2);
		listUsers.add(user1);
		listUsers.add(user2);
		
		assertEquals(2,listUsers.size());
	}
	
	public void testFindOne() {
		User user1 = new User("Luca Oddone", "Password", "lucaoddone@polito.it", 3);
		User user2 = new User("Paola Oddone","Password","paolaoddone@polito.it",4);
		
		listUsers.clear();
		user1.setUserId(1);
		user2.setUserId(2);
		listUsers.add(user1);
		listUsers.add(user2);
		
		assertEquals(1,findOne(1).getUserId());
		assertEquals(2,findOne(2).getUserId());
		
		assertNull(findOne(100));
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOne(Integer id) {
		Iterator<User> iter;
		
		iter = listUsers.iterator();
		while(iter.hasNext()) {
			User user = iter.next();
			if(user.getUserId() == id)
				return user;
		}
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		Iterator<User> iter;
		
		iter = listUsers.iterator();
		while(iter.hasNext()) {
			User user = iter.next();
			if(user.getUserId() == id)
				return true;
		}
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer id) {
		Iterator<User> iter;
		
		iter = listUsers.iterator();
		while(iter.hasNext()) {
			User user = iter.next();
			if(user.getUserId() == id) {
				listUsers.remove(user);
				return ;
			}
		}
		return;
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends User> S findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends User> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> findAll() {
		return listUsers;
	}

	@Override
	public List<User> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends User> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<User> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
