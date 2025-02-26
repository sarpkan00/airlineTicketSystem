package com.sarpkansavaskan.AirlineTicketSystem.repository;

import com.sarpkansavaskan.AirlineTicketSystem.model.AirlineFlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineFlightRepository extends JpaRepository<AirlineFlight, Integer> {
    Optional<AirlineFlight> findByAirlineFlightNumber(int airlineFlightNumber);


}
