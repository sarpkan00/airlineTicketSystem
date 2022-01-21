package com.sarpkansavaskan.AirlineTicketSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlightRouteDTO {
    private int id;
    private  String from;
    private  String to;
    @JsonIgnore
    private  List<AirlineFlightDTO> airlineFlights;

    public FlightRouteDTO(int id, String from, String to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }
}
