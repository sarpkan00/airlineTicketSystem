package com.sarpkansavaskan.AirlineTicketSystem.dto.converter;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirportDTO;
import com.sarpkansavaskan.AirlineTicketSystem.model.Airport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirportDtoConverter {
    public AirportDTO convert(Airport airport) {
        return new AirportDTO(
                airport.getId(),
                airport.getAirportName()
        );
    }

    public List<AirportDTO> convertToAirportDTOS(List<Airport> airports) {
        return airports
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
