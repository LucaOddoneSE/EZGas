package it.polito.ezgas.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidCarSharingException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.utils.Day;
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
	@Autowired
	private UserRepository userRepository;

	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		if(gasStationId<0)
			throw new InvalidGasStationException("Error! the GasStationId must not be negative");
		if(gasStationRepository.exists(gasStationId)) {
			System.out.println("The GasStation with the provided gasStationId is found!");
			System.out.println(gasStationRepository.findOne(gasStationId).getGasStationId() + " " +
					           gasStationRepository.findOne(gasStationId).getGasStationName() + " " +
					           gasStationRepository.findOne(gasStationId).getGasStationAddress());
			return gasStationConverter.toGasStationDto(gasStationRepository.findOne(gasStationId));
		}
		else {
			System.out.println("No GasStation was found with that gasStationId");
			return null;
		}
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		
		GasStation gasStation;
		
		if(gasStationDto == null) {
			System.out.println("Error! You have passed a null gasStationDto object!");
			return null;
		}
		
		if(gasStationDto.getGasStationId() == null) {
			if(gasStationDto.getHasDiesel() && gasStationDto.getDieselPrice() == null)
				gasStationDto.setDieselPrice((double) 0);
			if(gasStationDto.getHasGas() && gasStationDto.getGasPrice() == null)
				gasStationDto.setGasPrice((double) 0);
			if(gasStationDto.getHasSuperPlus() && gasStationDto.getSuperPlusPrice() == null)
				gasStationDto.setSuperPlusPrice((double) 0);
			if(gasStationDto.getHasSuper() && gasStationDto.getSuperPrice() == null)
				gasStationDto.setSuperPrice((double) 0);
			if(gasStationDto.getHasMethane() && gasStationDto.getMethanePrice() == null)
				gasStationDto.setMethanePrice((double) 0);
			if(gasStationDto.getHasPremiumDiesel() && gasStationDto.getPremiumDieselPrice() == null)
				gasStationDto.setPremiumDieselPrice((double) 0);
			
			if( (gasStationDto.getLon() < -180 || gasStationDto.getLon() >= 180) || 
					(gasStationDto.getLat() < -90 || gasStationDto.getLat() >= 90) )
					throw new GPSDataException("Error! GasStation containes wrong coordinates values");
			
			gasStationDto.setReportUser(null);
			gasStationDto.setUserDto(null);
			gasStationDto.setReportDependability(0);
			gasStationDto.setReportTimestamp(null);
			gasStation = gasStationRepository.save(gasStationConverter.toGasStation(gasStationDto));
			System.out.println("The GasStation is successfully saved!");
			return gasStationConverter.toGasStationDto(gasStationRepository.findOne(gasStation.getGasStationId()));
		}
		
		if( (gasStationDto.getHasDiesel() && gasStationDto.getDieselPrice() < 0) || 
			(gasStationDto.getHasGas() && gasStationDto.getGasPrice() < 0  ) || 
		    (gasStationDto.getHasSuper() && gasStationDto.getSuperPrice() < 0 ) ||
		    (gasStationDto.getHasSuperPlus() &&  gasStationDto.getSuperPlusPrice() < 0 ) || 
		    (gasStationDto.getHasMethane() && gasStationDto.getMethanePrice() < 0) ) 
			throw new PriceException("Error! One or more of the fuel types price is negative!");
		
		if( (gasStationDto.getLon() < -180 || gasStationDto.getLon() >= 180) || 
			(gasStationDto.getLat() < -90 || gasStationDto.getLat() >= 90) )
			throw new GPSDataException("Error! GasStation containes wrong coordinates values");
		
		gasStation = gasStationRepository.save(gasStationConverter.toGasStation(gasStationDto));
		System.out.println("The GasStation is successfully updated!");
		return gasStationConverter.toGasStationDto(gasStationRepository.findOne(gasStation.getGasStationId()));
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
			                    gasStation.getGasStationAddress());
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
		return false;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		
		List<GasStationDto> gs = getAllGasStations();
		if(gasolinetype == null)
			throw new InvalidGasTypeException("Error! You have passed a null gasolinetype as parameter");
		switch(gasolinetype) {
		  case "Diesel":
			  gs = gs.stream()
				.filter( (g) -> g.getHasDiesel())
				.sorted( (g1,g2) -> Double.compare(g1.getDieselPrice(), g2.getDieselPrice()) )
				.collect(Collectors.toList());
		    break;
		  case "Methane":
			  gs = gs.stream()
				.filter( (g) -> g.getHasMethane())
				.sorted( (g1,g2) -> Double.compare(g1.getMethanePrice(), g2.getMethanePrice()) )
				.collect(Collectors.toList());
		    break;
		  case "Gas":
			  gs = gs.stream()
				.filter( (g) -> g.getHasGas())
				.sorted( (g1,g2) -> Double.compare(g1.getGasPrice(), g2.getGasPrice()) )
				.collect(Collectors.toList());
		    break;
		  case "Super":
			  gs = gs.stream()
				.filter( (g) -> g.getHasSuper())
				.sorted( (g1,g2) -> Double.compare(g1.getSuperPrice(), g2.getSuperPrice()) )
				.collect(Collectors.toList());
		    break;
		  case "SuperPlus":
			  gs = gs.stream()
				.filter( (g) -> g.getHasSuperPlus())
				.sorted( (g1,g2) -> Double.compare(g1.getSuperPlusPrice(), g2.getSuperPlusPrice()) )
				.collect(Collectors.toList());
		    break;
		  case "PremiumDiesel":
			  gs = gs.stream()
				.filter( (g) -> g.getHasPremiumDiesel())
				.sorted( (g1,g2) -> Double.compare(g1.getPremiumDieselPrice(), g2.getPremiumDieselPrice()) )
				.collect(Collectors.toList());
		  default:
				throw new InvalidGasTypeException("Error! You have passed a non valid gasolinetype as parameter");
		}
		return gs;
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon, int radius) throws GPSDataException {
		if(radius<=0)
			radius = 1;
		if((lat < -90 || lat >= 90) || (lon < -180 || lon >= 180)) {
			throw new GPSDataException("coordinates out of bounds");
		}else {
			final int RADIUS = radius;
			return getAllGasStations().stream()
				.filter( (g) -> Haversine.distance(lat, lon, g.getLat(), g.getLon() ) <= RADIUS)
				.sorted( (g1,g2) -> Double.compare(Haversine.distance(lat, lon, g1.getLat(), g1.getLon() ), Haversine.distance(lat, lon, g2.getLat(), g2.getLon() ) ) )
				.collect(Collectors.toList());
			}
		}
	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, int radius,String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException {
		if(radius<=0)
			radius=1;
		if(gasolinetype == null)
			throw new InvalidGasTypeException("Error! You have passed a null gasolinetype as parameter");
		if(carsharing == null)
			throw new InvalidCarSharingException("Error! You have passed a null carsharing as a parameter");
		if((lat < -90 || lat >= 90) || (lon < -180 || lon >= 180))
			throw new GPSDataException("coordinates out of bounds");
		if(carsharing.equals("Enjoy") || carsharing.equals("Car2Go") || carsharing.equals("null")) {}
		else
			throw new InvalidCarSharingException("Error! It has been passed an invalid type for carsharing parameter");
		if(gasolinetype.equals("null")) { return new ArrayList<GasStationDto>(); }
		else {
			final int RADIUS = radius;
			List<GasStationDto> gs = getAllGasStations().stream()
					.filter( (g) -> Haversine.distance(lat, lon, g.getLat(), g.getLon() ) <= RADIUS)
					.sorted( (g1,g2) -> Double.compare(Haversine.distance(lat, lon, g1.getLat(), g1.getLon() ), Haversine.distance(lat, lon, g2.getLat(), g2.getLon() ) ) )
					.collect(Collectors.toList());
			if(carsharing.equals("null")){}
			else{
				gs = gs.stream()
					.filter( (g) -> g.getCarSharing().equals(carsharing))
					.collect(Collectors.toList());
			}
			switch(gasolinetype) {
			  case "Diesel":
				  gs = gs.stream()
					.filter( (g) -> g.getHasDiesel())
					.collect(Collectors.toList());
			    break;
			  case "Methane":
				  gs = gs.stream()
					.filter( (g) -> g.getHasMethane())
					.collect(Collectors.toList());
			    break;
			  case "Gas":
				  gs = gs.stream()
					.filter( (g) -> g.getHasGas())
					.collect(Collectors.toList());
			    break;
			  case "Super":
				  gs = gs.stream()
					.filter( (g) -> g.getHasSuper())
					.collect(Collectors.toList());
			    break;
			  case "SuperPlus":
				  gs = gs.stream()
					.filter( (g) -> g.getHasSuperPlus())
					.collect(Collectors.toList());
			    break;
			  case "PremiumDiesel":
				  gs = gs.stream()
					.filter( (g) -> g.getHasPremiumDiesel())
					.collect(Collectors.toList());
			  default:
					  throw new InvalidGasTypeException("Gas Type not supported");
			}
			return gs;
		}
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException,InvalidCarSharingException {
		if(gasolinetype == null)
			throw new InvalidGasTypeException("Error! You have passed a null gasolinetype as parameter");
		if(carsharing == null)
			throw new InvalidCarSharingException("Error! You have passed a null carsharing as a parameter");
		if(carsharing.equals("Enjoy") || carsharing.equals("Car2Go") || carsharing.equals("null")) {}
		else
			throw new InvalidCarSharingException("Error! It has been passed an invalid type for carsharing parameter");
		List<GasStationDto> gs = getAllGasStations();
		if(carsharing.equals("null") || gasolinetype.equals("null")){}
		else{
		gs = gs.stream()
				.filter( (g) -> g.getCarSharing().equals(carsharing))
				.collect(Collectors.toList());
		}
		switch(gasolinetype) {
		  case "Diesel":
			  gs = gs.stream()
				.filter( (g) -> g.getHasDiesel())
				.collect(Collectors.toList());
		    break;
		  case "Methane":
			  gs = gs.stream()
				.filter( (g) -> g.getHasMethane())
				.collect(Collectors.toList());
		    break;
		  case "Gas":
			  gs = gs.stream()
				.filter( (g) -> g.getHasGas())
				.collect(Collectors.toList());
		    break;
		  case "Super":
			  gs = gs.stream()
				.filter( (g) -> g.getHasSuper())
				.collect(Collectors.toList());
		    break;
		  case "SuperPlus":
			  gs = gs.stream()
				.filter( (g) -> g.getHasSuperPlus())
				.collect(Collectors.toList());
		    break;
		  case "PremiumDiesel":
			  gs = gs.stream()
				.filter( (g) -> g.getHasPremiumDiesel())
				.collect(Collectors.toList());
		  default:
			  throw new InvalidGasTypeException("Gas Type not supported");
		}
		return gs;
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		if(gasStationId < 0)
			throw new InvalidGasStationException("Error! the GasStationId must not be negative");
		if(userId < 0)
			throw new InvalidUserException("Error! Invalid userId: userId can't be negative");
		if(userRepository.exists(userId)) {
			if(gasStationRepository.exists(gasStationId)) {

				if( (gasStationRepository.findOne(gasStationId).getHasDiesel() && gasStationRepository.findOne(gasStationId).getDieselPrice() < 0) || 
					(gasStationRepository.findOne(gasStationId).getHasGas() && gasStationRepository.findOne(gasStationId).getGasPrice() < 0  ) || 
				    (gasStationRepository.findOne(gasStationId).getHasSuper() && gasStationRepository.findOne(gasStationId).getSuperPrice() < 0 ) ||
				    (gasStationRepository.findOne(gasStationId).getHasSuperPlus() &&   gasStationRepository.findOne(gasStationId).getSuperPlusPrice() < 0 ) || 
				    (gasStationRepository.findOne(gasStationId).getHasMethane() && gasStationRepository.findOne(gasStationId).getMethanePrice() < 0) ) 
						throw new PriceException("Error! One or more of the fuel types price is negative!");
				
				if( (gasStationRepository.findOne(gasStationId).getHasDiesel() && dieselPrice < 0) || 
					(gasStationRepository.findOne(gasStationId).getHasGas() && gasPrice < 0  ) || 
				    (gasStationRepository.findOne(gasStationId).getHasSuper() && superPrice < 0 ) ||
				    (gasStationRepository.findOne(gasStationId).getHasSuperPlus() &&  superPlusPrice < 0 ) || 
				    (gasStationRepository.findOne(gasStationId).getHasMethane() && methanePrice < 0) ) 
						throw new PriceException("Error! One or more of the fuel types price is negative!");
				
				double obsolence = 0;
				GasStation gasStation = gasStationRepository.findOne(gasStationId);
				if(gasStation.getReportTimestamp() == null && gasStation.getReportDependability() == 0) {
					System.out.println("You're going to report this gasStation for the first time!");
					gasStation.setReportUser(userId);
					gasStation.setUser(userRepository.findOne(userId));
					gasStation.setReportTimestamp(Day.calendarToString());
					
					if(gasStation.getHasDiesel())
						gasStation.setDieselPrice(dieselPrice);
					if(gasStation.getHasGas())
						gasStation.setGasPrice(gasPrice);
					if(gasStation.getHasSuper())
						gasStation.setSuperPrice(superPrice);
					if(gasStation.getHasSuperPlus())
						gasStation.setSuperPlusPrice(superPlusPrice);
					if(gasStation.getHasMethane())
						gasStation.setMethanePrice(methanePrice);
					
					System.out.println("ReportTimestamp: " + gasStation.getReportTimestamp());
					try {
						obsolence = (Day.calculateDays(gasStation.getReportTimestamp()));
						if(obsolence > 7)
							obsolence = 0;
						else
							obsolence = 1 - obsolence/7;
						System.out.println("obsolence value: " + obsolence + " (it should be 1)");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gasStation.setReportDependability(50*(userRepository.findOne(userId).getReputation()+5)/10+50*obsolence);
				}
				else {
					gasStation.setReportUser(userId);
					gasStation.setUser(userRepository.findOne(userId));
					System.out.println("ReportTimestamp: " + gasStation.getReportTimestamp());
					try {
						obsolence = (Day.calculateDays(gasStation.getReportTimestamp()));
						if(obsolence > 7)
							obsolence = 0;
						else
							obsolence = 1 - obsolence/7;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gasStation.setReportTimestamp(Day.calendarToString());
					System.out.println("ReportTimestamp: " + gasStation.getReportTimestamp());
					if(gasStation.getHasDiesel())
						gasStation.setDieselPrice(dieselPrice);
					if(gasStation.getHasGas())
						gasStation.setGasPrice(gasPrice);
					if(gasStation.getHasSuper())
						gasStation.setSuperPrice(superPrice);
					if(gasStation.getHasSuperPlus())
						gasStation.setSuperPlusPrice(superPlusPrice);
					if(gasStation.getHasMethane())
						gasStation.setMethanePrice(methanePrice);
					gasStation.setReportDependability(50*(userRepository.findOne(userId).getReputation()+5)/10+50*obsolence);
				}
				gasStationRepository.save((gasStationRepository.findOne(gasStationId)));
			}
			else {
				System.out.println("No GasStation with the following GasStationID: " + gasStationId + " " + "was found");
				return ;
			}
			
		}
		else {
			System.out.println("No user with the following userID: " + userId + " " + "was found");
			return ;
		}
		return ;
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		if(carSharing == null) {
			System.out.println("Error! You have passed a null carsharing as a parameter");
			return null;
		}
		ArrayList<GasStationDto> gs = new ArrayList<>();
		if(carSharing.equals("null")){}
		else{
		gs = getAllGasStations().stream()
				.filter( (g) -> g.getCarSharing().equals(carSharing))
				.collect(Collectors.toCollection(ArrayList::new));
		}
		return gs;
	}
}