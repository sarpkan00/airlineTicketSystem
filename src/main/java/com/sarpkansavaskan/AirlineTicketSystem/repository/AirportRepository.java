package com.sarpkansavaskan.AirlineTicketSystem.repository;

import com.sarpkansavaskan.AirlineTicketSystem.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
    Optional<Airport> findByAirportName(String airportName);


}
