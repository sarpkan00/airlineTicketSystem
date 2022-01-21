package com.sarpkansavaskan.AirlineTicketSystem.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateAirlineFlightRequest {
    private int airlineFlightNumber;
    private int passengers;
    private double price;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    private int airlineCompanyId;
    private int flightRouteId;
}
