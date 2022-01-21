package com.sarpkansavaskan.AirlineTicketSystem.dto.request;

import com.sarpkansavaskan.AirlineTicketSystem.model.enums.TicketEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightTicketRequest {
    private long passengerNationalId;
    private String passengerFirstName;
    private String passengerLastName;
    private long ticketNumber;
    private String creditCard;
    private int airlineFlightId;
}
