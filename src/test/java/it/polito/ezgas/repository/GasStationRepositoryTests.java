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

import it.polito.ezgas.entity.GasStation;

public class GasStationRepositoryTests implements JpaRepository<GasStation,Integer> {
	
	private List<GasStation> listGasStation = new ArrayList<>();
	
	@Test
	public void testExist() {
		GasStation gasStation1 = new GasStation("GasStation1","Via Italia 1",true,true,false,false,true,
				"BlaBlaCar",110.574,81.320,1.25,1.55,0,0,0.90,null,null,0);
		GasStation gasStation2 = new GasStation("GasStation2","Via Italia 2",false,false,true,true,false,
				"BlaBlaCar",110.649,87.550,0,0,1.25,1.55,0,null,null,0);
		
		gasStation1.setGasStationId(1);
		gasStation2.setGasStationId(2);
		
		listGasStation.add(gasStation1);
		listGasStation.add(gasStation2);
		
		assertTrue(exists(1));
		assertTrue(exists(2));
		assertFalse(exists(100));
		
	}

	private void assertFalse(boolean exists) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<GasStation> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends GasStation> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GasStation findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		Iterator<GasStation> iter = listGasStation.iterator();
		
		while(iter.hasNext()) {
			GasStation gasStation = iter.next();
			if(gasStation.getGasStationId() == id)
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(GasStation entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends GasStation> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends GasStation> S findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends GasStation> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends GasStation> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends GasStation> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<GasStation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GasStation> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GasStation> findAll(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends GasStation> List<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends GasStation> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<GasStation> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GasStation getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends GasStation> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends GasStation> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

}
