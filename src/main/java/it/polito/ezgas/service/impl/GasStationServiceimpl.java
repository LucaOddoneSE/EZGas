package it.polito.ezgas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.dto.GasStationDto;
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

	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		// TODO Auto-generated method stub
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
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		if((lat < -90 || lat > 90) || (lon < -90 || lon > 90)) {
			throw new GPSDataException("coordinates out of bounds");
		}else {
			return getAllGasStations().stream()
				.filter( (g) -> Haversine.distance(lat, lon, g.getLat(), g.getLon() ) < 1)
				.collect(Collectors.toList());
			}
		}
	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		if((lat < -90 || lat > 90) || (lon < -90 || lon > 90)) {
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
