package com.sarpkansavaskan.AirlineTicketSystem.dto;

import com.sarpkansavaskan.AirlineTicketSystem.model.enums.TicketEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlightTicketDTO {
    private int id;
    private long passengerNationalId;
    private  String passengerFirstName;
    private String passengerLastName;
    private long ticketNumber;
    private  String creditCard;
    private TicketEnum isActive;
}
