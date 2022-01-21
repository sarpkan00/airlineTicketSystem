package com.sarpkansavaskan.AirlineTicketSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlightTicketRequest {
    private long passengerNationalId;
    private String passengerFirstName;
    private String passengerLastName;
    private long ticketNumber;
    private int airlineFlightId;
}
