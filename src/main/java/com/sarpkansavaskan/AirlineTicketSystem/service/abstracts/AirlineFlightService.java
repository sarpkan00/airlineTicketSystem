package com.sarpkansavaskan.AirlineTicketSystem.service.abstracts;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineFlightDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirlineFlightRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirlineFlightRequest;

import java.util.List;

public interface AirlineFlightService {
    AirlineFlightDTO add(CreateAirlineFlightRequest request);
    AirlineFlightDTO updateById(int id, UpdateAirlineFlightRequest request);
    void deleteById(int id);
    AirlineFlightDTO findByAirlineFlightNumber(int airlineFlightNumber);
    AirlineFlightDTO findById(int id);
    List<AirlineFlightDTO> getAll();
}
