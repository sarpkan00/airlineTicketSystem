package com.sarpkansavaskan.AirlineTicketSystem.dto.converter;

import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightRouteDTO;
import com.sarpkansavaskan.AirlineTicketSystem.model.FlightRoute;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightRouteDtoConverter extends BaseDtoConverter{
    public FlightRouteDTO convert(FlightRoute flightRoute) {
        return new FlightRouteDTO(
                flightRoute.getId(),
                flightRoute.getFrom(),
                flightRoute.getTo()

        );
    }

    public List<FlightRouteDTO> convertToFlightRouteDTOS(List<FlightRoute> flightRoutes) {
        return flightRoutes
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
