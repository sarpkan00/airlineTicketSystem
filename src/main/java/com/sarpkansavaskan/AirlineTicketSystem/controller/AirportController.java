package com.sarpkansavaskan.AirlineTicketSystem.controller;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirportDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirportRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirportRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sarpkansavaskan.AirlineTicketSystem.service.abstracts.AirportService;

import java.util.List;

@RestController
@RequestMapping("/airports")
@CrossOrigin
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping()
    public ResponseEntity<AirportDTO> add(@RequestBody CreateAirportRequest request){
        return ResponseEntity.ok(airportService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportDTO> update(@PathVariable int id, @RequestBody UpdateAirportRequest request){
        return ResponseEntity.ok(airportService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        airportService.deleteById(id);
    }

    @GetMapping("/search/{airportName}")
    public ResponseEntity<AirportDTO> findByAirportName(@PathVariable String airportName){
       return ResponseEntity.ok(airportService.findByAirportName(airportName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(airportService.findById(id));

    }

    @GetMapping()
    public ResponseEntity<List<AirportDTO>> getAll(){
        return ResponseEntity.ok(airportService.getAll());
    }
}
