package com.sarpkansavaskan.AirlineTicketSystem.controller;

import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightTicketDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateFlightTicketRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateFlightTicketRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sarpkansavaskan.AirlineTicketSystem.service.abstracts.FlightTicketService;

import java.util.List;

@RestController
@RequestMapping("/flightTickets")
@CrossOrigin
public class FlightTicketController {

    private final FlightTicketService flightTicketService;

    public FlightTicketController(FlightTicketService flightTicketService) {
        this.flightTicketService = flightTicketService;
    }

    @PostMapping()
    public ResponseEntity<FlightTicketDTO> add(@RequestBody CreateFlightTicketRequest request){
       return ResponseEntity.ok(flightTicketService.buyTicket(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightTicketDTO> update(@PathVariable int id, @RequestBody UpdateFlightTicketRequest request){
        return ResponseEntity.ok(flightTicketService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightTicketDTO> cancelTicket(@PathVariable int id){
        return ResponseEntity.ok(flightTicketService.cancelTicket(id));

    }

    @GetMapping("/search/{ticketNumber}")
    public ResponseEntity<FlightTicketDTO> findByTicketNumber(@PathVariable long ticketNumber){
        return ResponseEntity.ok(flightTicketService.findByTicketNumber(ticketNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightTicketDTO> findById(@PathVariable int id){
        return ResponseEntity.ok(flightTicketService.findById(id));

    }

    @GetMapping()
    public ResponseEntity<List<FlightTicketDTO>> getAll(){
        return ResponseEntity.ok(flightTicketService.getAll());
    }
}
