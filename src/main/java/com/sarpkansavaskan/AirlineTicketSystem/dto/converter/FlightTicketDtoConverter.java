package com.sarpkansavaskan.AirlineTicketSystem.dto.converter;

import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightTicketDTO;
import com.sarpkansavaskan.AirlineTicketSystem.model.FlightTicket;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightTicketDtoConverter {
    public FlightTicketDTO convert(FlightTicket flightTicket) {
        return new FlightTicketDTO(
                flightTicket.getId(),
                flightTicket.getPassengerNationalId(),
                flightTicket.getPassengerFirstName(),
                flightTicket.getPassengerLastName(),
                flightTicket.getTicketNumber(),
                maskCC(flightTicket.getCreditCard()),
                flightTicket.getIsActive()
        );
    }

    public List<FlightTicketDTO> convertToFlightTicketDTOS(List<FlightTicket> flightTickets) {
        return flightTickets
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private String maskCC(String creditCard) {
        String fixCC = String.valueOf(creditCard);
        String privateCC = fixCC.replaceAll("[^\\d.]", "");

        return privateCC.substring(0, 6) + "******" + privateCC.substring(13);
    }
}
