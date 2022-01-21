package com.sarpkansavaskan.AirlineTicketSystem.controller;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineFlightDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirlineFlightRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirlineFlightRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sarpkansavaskan.AirlineTicketSystem.service.abstracts.AirlineFlightService;

import java.util.List;

@RestController
@RequestMapping("/airlinesFlighties")
@CrossOrigin
public class AirlineFlightController {

    private final AirlineFlightService airlineFlightService;

    public AirlineFlightController(AirlineFlightService airlineFlightService) {
        this.airlineFlightService = airlineFlightService;
    }

    @PostMapping()
    public ResponseEntity<AirlineFlightDTO> add(@RequestBody CreateAirlineFlightRequest request){
        return ResponseEntity.ok(airlineFlightService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineFlightDTO> update(@PathVariable int id, @RequestBody UpdateAirlineFlightRequest request){
        return ResponseEntity.ok(airlineFlightService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        airlineFlightService.deleteById(id);
    }

    @GetMapping("/search/{airlineFlightNumber}")
    public ResponseEntity<AirlineFlightDTO> findByAirlineFlightNumber(@PathVariable int airlineFlightNumber){
        return ResponseEntity.ok(airlineFlightService.findByAirlineFlightNumber(airlineFlightNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineFlightDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(airlineFlightService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<AirlineFlightDTO>> getAll(){
        return ResponseEntity.ok(airlineFlightService.getAll());
    }
}
