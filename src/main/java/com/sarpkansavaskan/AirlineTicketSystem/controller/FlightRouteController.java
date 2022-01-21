package com.sarpkansavaskan.AirlineTicketSystem.controller;

import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightRouteDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateFlightRouteRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateFlightRouteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sarpkansavaskan.AirlineTicketSystem.service.abstracts.FlightRouteService;

import java.util.List;

@RestController
@RequestMapping("/flightRoutes")
@CrossOrigin
public class FlightRouteController {

    private final FlightRouteService flightRouteService;

    public FlightRouteController(FlightRouteService flightRouteService) {
        this.flightRouteService = flightRouteService;
    }

    @PostMapping()
    public ResponseEntity<FlightRouteDTO> add(@RequestBody CreateFlightRouteRequest request) {
        return ResponseEntity.ok(flightRouteService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightRouteDTO> update(@PathVariable int id, @RequestBody UpdateFlightRouteRequest request) {
        return ResponseEntity.ok(flightRouteService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        flightRouteService.deleteById(id);
    }

    @GetMapping("/search/{from}")
    public ResponseEntity<FlightRouteDTO> findByFrom(@PathVariable String from) {
        return ResponseEntity.ok(flightRouteService.findByFrom(from));
    }

    @GetMapping("/search/{to}")
    public ResponseEntity<FlightRouteDTO> findByTo(@PathVariable String to) {
        return ResponseEntity.ok(flightRouteService.findByTo(to));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightRouteDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(flightRouteService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<FlightRouteDTO>> getAll() {
        return ResponseEntity.ok(flightRouteService.getAll());
    }
}
