package com.sarpkansavaskan.AirlineTicketSystem.service.abstracts;

import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightTicketDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateFlightTicketRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateFlightTicketRequest;

import java.util.List;

public interface FlightTicketService {

    FlightTicketDTO buyTicket(CreateFlightTicketRequest request);
    FlightTicketDTO updateById(int id, UpdateFlightTicketRequest request);
    FlightTicketDTO cancelTicket(int id);

   FlightTicketDTO findByTicketNumber(long ticketNumber);

    FlightTicketDTO findById(int id);
    List<FlightTicketDTO> getAll();
}
