package com.sarpkansavaskan.AirlineTicketSystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AirlineCompanyDTO {
    private int id;
    private String companyName;
    private String phoneNumber;
    private List<AirlineFlightDTO> airlineFlights;

    public AirlineCompanyDTO(int id, String companyName, String phoneNumber, List<AirlineFlightDTO> airlineFlights) {
        this.id = id;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.airlineFlights = airlineFlights;
    }
}
