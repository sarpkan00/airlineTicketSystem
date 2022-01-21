package com.sarpkansavaskan.AirlineTicketSystem.repository;

import com.sarpkansavaskan.AirlineTicketSystem.model.FlightTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightTicketRepository extends JpaRepository<FlightTicket, Integer> {
    Optional<FlightTicket> findByTicketNumber(long ticketNumber);
}
