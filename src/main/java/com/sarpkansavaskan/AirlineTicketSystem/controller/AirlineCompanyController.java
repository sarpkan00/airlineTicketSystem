package com.sarpkansavaskan.AirlineTicketSystem.controller;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineCompanyDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirlineCompanyRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirlineCompanyRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sarpkansavaskan.AirlineTicketSystem.service.abstracts.AirlineCompanyService;

import java.util.List;

@RestController
@RequestMapping("/airlineCompanies")
@CrossOrigin
public class AirlineCompanyController {

    private final AirlineCompanyService airlineCompanyService;

    public AirlineCompanyController(AirlineCompanyService airlineCompanyService) {
        this.airlineCompanyService = airlineCompanyService;
    }

    @PostMapping()
    public ResponseEntity<AirlineCompanyDTO> add(@RequestBody CreateAirlineCompanyRequest request) {
        return ResponseEntity.ok(airlineCompanyService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineCompanyDTO> update(@PathVariable int id, @RequestBody UpdateAirlineCompanyRequest request) {
        return ResponseEntity.ok(airlineCompanyService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        airlineCompanyService.deleteById(id);
    }

    //TOPARLA
    @GetMapping("/search/{companyName}")
    public AirlineCompanyDTO searchByCompanyName(@PathVariable String companyName) {
        return airlineCompanyService.findByCompanyName(companyName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineCompanyDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(airlineCompanyService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<AirlineCompanyDTO>> getAll() {
        return ResponseEntity.ok(airlineCompanyService.getAll());
    }
}
