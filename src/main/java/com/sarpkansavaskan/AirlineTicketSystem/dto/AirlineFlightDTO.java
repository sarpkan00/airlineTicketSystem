package com.sarpkansavaskan.AirlineTicketSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sarpkansavaskan.AirlineTicketSystem.model.FlightTicket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AirlineFlightDTO {
    private int id;
    private int airlineFlightNumber;
    private  int passengers;
    private  double price;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    private   String airlineCompanyName;
    private int flightRouteId;
    private List<FlightTicketDTO> flightTicket;

    public AirlineFlightDTO(int id, int airlineFlightNumber, int passengers, double price, Date date, String airlineCompanyName, int flightRouteId, List<FlightTicketDTO> flightTicket) {
        this.id = id;
        this.airlineFlightNumber = airlineFlightNumber;
        this.passengers = passengers;
        this.price = price;
        this.date = date;
        this.airlineCompanyName = airlineCompanyName;
        this.flightRouteId = flightRouteId;
        this.flightTicket = flightTicket;
    }
}
