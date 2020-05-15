package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.utils.Haversine;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceimpl implements GasStationService {
	
	@Autowired
	private GasStationRepository gasStationRepository;
	@Autowired
	private GasStationConverter gasStationConverter;

	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		if(gasStationId<0)
			throw new InvalidGasStationException("Error! the GasStationId must not be negative");
		if(gasStationRepository.exists(gasStationId)) {
			System.out.println("The GasStation with the provided gasStationId is found!");
			System.out.println(gasStationRepository.findOne(gasStationId).getGasStationId() + " " +
					           gasStationRepository.findOne(gasStationId).getGasStationName() + " " +
					           gasStationRepository.findOne(gasStationId).getGasStationAddress() + " " +
					           gasStationRepository.findOne(gasStationId).getReportTimestamp());
			return gasStationConverter.toGasStationDto(gasStationRepository.findOne(gasStationId));
		}
		else {
			System.out.println("No GasStation was found with that gasStationId");
			return null;
		}
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		
		if(gasStationDto == null) {
			System.out.println("Error! You have passed a null gasStationDto object!");
			return null;
		}
		
		if( gasStationDto.getDieselPrice() < 0 || gasStationDto.getGasPrice() < 0 || 
		    gasStationDto.getSuperPrice() < 0  || gasStationDto.getSuperPlusPrice() < 0 || 
		    gasStationDto.getMethanePrice() < 0) 
			throw new PriceException("Error! One or more of the fuel types price is negative!");
		
		if( (gasStationDto.getLon() < -180 || gasStationDto.getLon() >= 180) || 
			(gasStationDto.getLat() < -90 || gasStationDto.getLat() >= 90) )
			throw new GPSDataException("Error! GasStation containes wrong coordinates values");
		
		if(gasStationDto.getHasDiesel() && gasStationDto.getDieselPrice()<= 0)
			gasStationDto.setDieselPrice(0);
		if(gasStationDto.getHasGas() && gasStationDto.getGasPrice() <=0)
			gasStationDto.setGasPrice(0);
		if(gasStationDto.getHasSuperPlus() && gasStationDto.getSuperPlusPrice() <=0)
			gasStationDto.setSuperPlusPrice(0);
		if(gasStationDto.getHasSuper() && gasStationDto.getSuperPrice() <=0)
			gasStationDto.setSuperPrice(0);
		if(gasStationDto.getHasMethane() && gasStationDto.getMethanePrice() <=0)
			gasStationDto.setMethanePrice(0);
		
		gasStationRepository.save(gasStationConverter.toGasStation(gasStationDto));
		System.out.println("Tha GasStation passed is successfully saved!");
		return gasStationDto;
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		GasStation gasStation;
		List<GasStationDto> listGasStationDto = new ArrayList<>();
		Iterator<GasStation> iter = gasStationRepository.findAll().iterator();
		if(gasStationRepository.findAll().size() == 0) {
			System.out.println("No gasStation exists");
			return listGasStationDto;
		}
		while(iter.hasNext()) {
			gasStation = iter.next();
			System.out.println( gasStation.getGasStationId() + " " +
			                    gasStation.getGasStationName() + " " +
			                    gasStation.getGasStationAddress() + " " +
			                    gasStation.getReportTimestamp() );
			listGasStationDto.add(gasStationConverter.toGasStationDto(gasStation));
		}
		return listGasStationDto;
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		if(gasStationId < 0)
			throw new InvalidGasStationException("Error! It has been passed an invalid gasStationId(<0)");
		if(gasStationRepository.exists(gasStationId)) {
			gasStationRepository.delete(gasStationId);
			System.out.println("GasStation successfully deleted!");
			return true;
		}
		return null;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		
		List<GasStationDto> gs = getAllGasStations();
		switch(gasolinetype) {
		  case "diesel":
			  gs = gs.stream()
				.filter( (g) -> g.getHasDiesel())
				.sorted( (g1,g2) -> Double.compare(g1.getDieselPrice(), g2.getDieselPrice()) )
				.collect(Collectors.toList());
		    break;
		  case "methane":
			  gs = gs.stream()
				.filter( (g) -> g.getHasMethane())
				.sorted( (g1,g2) -> Double.compare(g1.getMethanePrice(), g2.getMethanePrice()) )
				.collect(Collectors.toList());
		    break;
		  case "gas":
			  gs = gs.stream()
				.filter( (g) -> g.getHasGas())
				.sorted( (g1,g2) -> Double.compare(g1.getGasPrice(), g2.getGasPrice()) )
				.collect(Collectors.toList());
		    break;
		  case "super":
			  gs = gs.stream()
				.filter( (g) -> g.getHasSuper())
				.sorted( (g1,g2) -> Double.compare(g1.getSuperPrice(), g2.getSuperPrice()) )
				.collect(Collectors.toList());
		    break;
		  case "superplus":
			  gs = gs.stream()
				.filter( (g) -> g.getHasSuperPlus())
				.sorted( (g1,g2) -> Double.compare(g1.getSuperPlusPrice(), g2.getSuperPlusPrice()) )
				.collect(Collectors.toList());
		    break;
		  default:
			  if(gasolinetype != null)
			  throw new InvalidGasTypeException("Gas Type not supported");
		}
		return gs;
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		if((lat < -90 || lat > 90) || (lon < -180 || lon > 180)) {
			throw new GPSDataException("coordinates out of bounds");
		}else {
			return getAllGasStations().stream()
				.filter( (g) -> Haversine.distance(lat, lon, g.getLat(), g.getLon() ) < 1)
				.sorted( (g1,g2) -> Double.compare(Haversine.distance(lat, lon, g1.getLat(), g1.getLon() ), Haversine.distance(lat, lon, g2.getLat(), g2.getLon() ) ) )
				.collect(Collectors.toList());
			}
		}
	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		if((lat < -90 || lat > 90) || (lon < -180 || lon > 180)) {
			throw new GPSDataException("coordinates out of bounds");
		}else {
			List<GasStationDto> gs = getAllGasStations().stream()
					.filter( (g) -> Haversine.distance(lat, lon, g.getLat(), g.getLon() ) < 1)
					.collect(Collectors.toList());
			if(carsharing != null) {
				gs = gs.stream()
						.filter( (g) -> g.getCarSharing().equals(carsharing))
						.collect(Collectors.toList());
			}
			switch(gasolinetype) {
			  case "diesel":
				  gs = gs.stream()
					.filter( (g) -> g.getHasDiesel())
					.collect(Collectors.toList());
			    break;
			  case "methane":
				  gs = gs.stream()
					.filter( (g) -> g.getHasMethane())
					.collect(Collectors.toList());
			    break;
			  case "gas":
				  gs = gs.stream()
					.filter( (g) -> g.getHasGas())
					.collect(Collectors.toList());
			    break;
			  case "super":
				  gs = gs.stream()
					.filter( (g) -> g.getHasSuper())
					.collect(Collectors.toList());
			    break;
			  case "superplus":
				  gs = gs.stream()
					.filter( (g) -> g.getHasSuperPlus())
					.collect(Collectors.toList());
			    break;
			  default:
				  if(gasolinetype != null)
				  throw new InvalidGasTypeException("Gas Type not supported");
			}
			return gs;
		}
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		//there is to method description so i dont know if it does what it needs
		List<GasStationDto> gs = getAllGasStations();
		if(carsharing != null) {
			gs = gs.stream()
					.filter( (g) -> g.getCarSharing().equals(carsharing))
					.collect(Collectors.toList());
		}
		switch(gasolinetype) {
		  case "diesel":
			  gs = gs.stream()
				.filter( (g) -> g.getHasDiesel())
				.collect(Collectors.toList());
		    break;
		  case "methane":
			  gs = gs.stream()
				.filter( (g) -> g.getHasMethane())
				.collect(Collectors.toList());
		    break;
		  case "gas":
			  gs = gs.stream()
				.filter( (g) -> g.getHasGas())
				.collect(Collectors.toList());
		    break;
		  case "super":
			  gs = gs.stream()
				.filter( (g) -> g.getHasSuper())
				.collect(Collectors.toList());
		    break;
		  case "superplus":
			  gs = gs.stream()
				.filter( (g) -> g.getHasSuperPlus())
				.collect(Collectors.toList());
		    break;
		  default:
			  if(gasolinetype != null)
			  throw new InvalidGasTypeException("Gas Type not supported");
		}
		return gs;
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		return getAllGasStations().stream()
				.filter( (g) -> g.getCarSharing().equals(carSharing))
				.collect(Collectors.toList());
	}
	

}
