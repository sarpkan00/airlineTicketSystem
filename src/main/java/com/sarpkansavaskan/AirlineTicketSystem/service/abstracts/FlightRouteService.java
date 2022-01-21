package com.sarpkansavaskan.AirlineTicketSystem.service.abstracts;

import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightRouteDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateFlightRouteRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateFlightRouteRequest;
import com.sarpkansavaskan.AirlineTicketSystem.model.FlightRoute;

import java.util.List;
import java.util.Optional;

public interface FlightRouteService {

    FlightRouteDTO add(CreateFlightRouteRequest request);
    FlightRouteDTO updateById(int id, UpdateFlightRouteRequest request);
    void deleteById(int id);

    FlightRouteDTO findByFrom(String from);
    FlightRouteDTO findByTo(String to);

    FlightRouteDTO findById(int id);
    List<FlightRouteDTO> getAll();
}
