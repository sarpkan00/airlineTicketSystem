package com.sarpkansavaskan.AirlineTicketSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportDTO {
    private int id;
    private  String airportName;

    public AirportDTO(String airportName) {
        this.airportName = airportName;
    }

    public AirportDTO(int id, String airportName) {
        this.id = id;
        this.airportName = airportName;
    }
}
