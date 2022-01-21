package com.sarpkansavaskan.AirlineTicketSystem.repository;


import com.sarpkansavaskan.AirlineTicketSystem.model.FlightRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRouteRepository extends JpaRepository<FlightRoute, Integer> {
    Optional<FlightRoute> findByFrom(String from);
    Optional<FlightRoute> findByTo(String to);
}
