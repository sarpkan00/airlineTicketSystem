package com.sarpkansavaskan.AirlineTicketSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFlightRouteRequest {
    private String from;
    private String to;
}
