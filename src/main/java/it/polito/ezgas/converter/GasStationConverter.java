package it.polito.ezgas.converter;

import org.springframework.stereotype.Component;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

@Component
public class GasStationConverter {
	
	private GasStation gasStation;
	private GasStationDto gasStationDto;
	private UserConverter userConverter = new UserConverter();
	
	public GasStationConverter() {
		
	}
	
	public GasStationDto toGasStationDto(GasStation gasStationSource) {
		
		gasStationDto = new GasStationDto();
		
		gasStationDto.setGasStationId(gasStationSource.getGasStationId());
		gasStationDto.setGasStationName(gasStationSource.getGasStationName());
		gasStationDto.setGasStationAddress(gasStationSource.getGasStationAddress());
		gasStationDto.setHasDiesel(gasStationSource.getHasDiesel());
		gasStationDto.setHasSuper(gasStationSource.getHasSuper());
		gasStationDto.setHasSuperPlus(gasStationSource.getHasSuperPlus());
		gasStationDto.setHasGas(gasStationSource.getHasGas());
		gasStationDto.setHasMethane(gasStationSource.getHasMethane());
		if(gasStationSource.getCarSharing() != null)
			if(gasStationSource.getCarSharing().equals("null"))
			gasStationDto.setCarSharing(null);
		else
			gasStationDto.setCarSharing(gasStationSource.getCarSharing());
		gasStationDto.setLat(gasStationSource.getLat());
		gasStationDto.setLon(gasStationSource.getLon());
		gasStationDto.setDieselPrice(gasStationSource.getDieselPrice());
		gasStationDto.setSuperPrice(gasStationSource.getSuperPrice());
		gasStationDto.setSuperPlusPrice(gasStationSource.getSuperPlusPrice());
		gasStationDto.setGasPrice(gasStationSource.getGasPrice());
		gasStationDto.setMethanePrice(gasStationSource.getMethanePrice());
		gasStationDto.setReportUser(gasStationSource.getReportUser());
		if(gasStationSource.getUser() == null){}
		else {
			gasStationDto.setUserDto(userConverter.toUserDto(gasStationSource.getUser()));
		}
		gasStationDto.setReportTimestamp(gasStationSource.getReportTimestamp());
		gasStationDto.setReportDependability(gasStationSource.getReportDependability());
		
		return gasStationDto;
	}
	
	public GasStation toGasStation(GasStationDto gasStationDtoSource) {
		gasStation = new GasStation();
		
		gasStation.setGasStationId(gasStationDtoSource.getGasStationId());
		gasStation.setGasStationName(gasStationDtoSource.getGasStationName());
		gasStation.setGasStationAddress(gasStationDtoSource.getGasStationAddress());
		gasStation.setHasDiesel(gasStationDtoSource.getHasDiesel());
		gasStation.setHasSuper(gasStationDtoSource.getHasSuper());
		gasStation.setHasSuperPlus(gasStationDtoSource.getHasSuperPlus());
		gasStation.setHasGas(gasStationDtoSource.getHasGas());
		gasStation.setHasMethane(gasStationDtoSource.getHasMethane());
		if(gasStationDtoSource.getCarSharing() != null)
			if(gasStationDtoSource.getCarSharing().equals("null"))
				gasStation.setCarSharing(null);
		else
			gasStation.setCarSharing(gasStationDtoSource.getCarSharing());
		gasStation.setLat(gasStationDtoSource.getLat());
		gasStation.setLon(gasStationDtoSource.getLon());
		gasStation.setDieselPrice(gasStationDtoSource.getDieselPrice());
		gasStation.setSuperPrice(gasStationDtoSource.getSuperPrice());
		gasStation.setSuperPlusPrice(gasStationDtoSource.getSuperPlusPrice());
		gasStation.setGasPrice(gasStationDtoSource.getGasPrice());
		gasStation.setMethanePrice(gasStationDtoSource.getMethanePrice());
		gasStation.setReportUser(gasStationDtoSource.getReportUser());
		if(gasStationDtoSource.getUserDto() == null) {}
		else {
			gasStation.setUser(userConverter.toUser(gasStationDtoSource.getUserDto()));
		}
		gasStation.setReportTimestamp(gasStationDtoSource.getReportTimestamp());
		gasStation.setReportDependability(gasStationDtoSource.getReportDependability());
		
		return gasStation;
	}

}
