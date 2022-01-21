package com.sarpkansavaskan.AirlineTicketSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAirlineFlightRequest {
    private int passengers;
    private double price;
}
