package com.sarpkansavaskan.AirlineTicketSystem.dto.converter;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineFlightDTO;
import com.sarpkansavaskan.AirlineTicketSystem.model.AirlineFlight;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirlineFlightDtoConverter extends BaseDtoConverter {

    public AirlineFlightDTO convert(AirlineFlight airlineFlight) {
        return new AirlineFlightDTO(
                airlineFlight.getId(),
                airlineFlight.getAirlineFlightNumber(),
                airlineFlight.getPassengers(),
                airlineFlight.getPrice(),
                airlineFlight.getDate(),
                airlineFlight.getAirlineCompany().getCompanyName(),
                airlineFlight.getFlightRoute().getId(),
                getFlightTicketDtos(airlineFlight.getFlightTicket())
        );
    }

    public List<AirlineFlightDTO> convertToAirlineFlightDTOS(List<AirlineFlight> airlineFlights) {
        return airlineFlights
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
