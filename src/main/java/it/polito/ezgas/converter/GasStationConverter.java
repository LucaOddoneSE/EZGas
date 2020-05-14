package it.polito.ezgas.converter;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

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
		gasStationDto.setCarSharing(gasStationSource.getCarSharing());
		gasStationDto.setLat(gasStationSource.getLat());
		gasStationDto.setLon(gasStationSource.getLon());
		gasStationDto.setDieselPrice(gasStationSource.getDieselPrice());
		gasStationDto.setSuperPrice(gasStationSource.getSuperPrice());
		gasStationDto.setSuperPlusPrice(gasStationSource.getSuperPlusPrice());
		gasStationDto.setGasPrice(gasStationSource.getGasPrice());
		gasStationDto.setMethanePrice(gasStationSource.getMethanePrice());
		gasStationDto.setReportUser(gasStationSource.getReportUser());
		gasStationDto.setUserDto(userConverter.toUserDto(gasStationSource.getUser()));
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
		gasStation.setCarSharing(gasStationDtoSource.getCarSharing());
		gasStation.setLat(gasStationDtoSource.getLat());
		gasStation.setLon(gasStationDtoSource.getLon());
		gasStation.setDieselPrice(gasStationDtoSource.getDieselPrice());
		gasStation.setSuperPrice(gasStationDtoSource.getSuperPrice());
		gasStation.setSuperPlusPrice(gasStationDtoSource.getSuperPlusPrice());
		gasStation.setGasPrice(gasStationDtoSource.getGasPrice());
		gasStation.setMethanePrice(gasStationDtoSource.getMethanePrice());
		gasStation.setReportUser(gasStationDtoSource.getReportUser());
		gasStation.setUser(userConverter.toUser(gasStationDtoSource.getUserDto()));
		gasStation.setReportTimestamp(gasStationDtoSource.getReportTimestamp());
		gasStation.setReportDependability(gasStationDtoSource.getReportDependability());
		
		return gasStation;
	}

}
