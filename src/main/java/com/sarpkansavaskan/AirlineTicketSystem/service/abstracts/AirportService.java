package com.sarpkansavaskan.AirlineTicketSystem.service.abstracts;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirportDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirportRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirportRequest;

import java.util.List;

public interface AirportService {

    AirportDTO add(CreateAirportRequest request);
    AirportDTO updateById(int id, UpdateAirportRequest request);
    void deleteById(int id);

    AirportDTO findByAirportName(String airportName);

    AirportDTO findById(int id);
    List<AirportDTO> getAll();
}
