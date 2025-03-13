package com.gtel.srpingtutorial.mapper;


import com.gtel.srpingtutorial.dto.request.AirportRequest;
import com.gtel.srpingtutorial.dto.response.AirportResponse;
import com.gtel.srpingtutorial.entity.Airport;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    Airport toAirport(AirportRequest airportRequest);

    AirportResponse toAirportResponse(Airport airport);

    @Mapping(target = "iata", ignore = true)
    void updateAirport(@MappingTarget Airport airport, AirportRequest airportRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePatchAirport(@MappingTarget Airport airport, AirportRequest airportRequest);
}
