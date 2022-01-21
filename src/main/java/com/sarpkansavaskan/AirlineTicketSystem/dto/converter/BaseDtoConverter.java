package com.sarpkansavaskan.AirlineTicketSystem.dto.converter;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineFlightDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightTicketDTO;
import com.sarpkansavaskan.AirlineTicketSystem.model.AirlineFlight;
import com.sarpkansavaskan.AirlineTicketSystem.model.FlightTicket;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BaseDtoConverter {

    protected List<AirlineFlightDTO> getAirlineFlightDtos(List<AirlineFlight> airlineFlights) {
        return airlineFlights.stream()
                .map(airlineFlight -> new AirlineFlightDTO(
                                airlineFlight.getId(),
                                airlineFlight.getAirlineFlightNumber(),
                                airlineFlight.getPassengers(),
                                airlineFlight.getPrice(),
                                airlineFlight.getDate(),
                                airlineFlight.getAirlineCompany().getCompanyName(),
                                airlineFlight.getFlightRoute().getId(),
                                getFlightTicketDtos(airlineFlight.getFlightTicket())
                        )
                ).collect(Collectors.toList());
    }

    protected List<FlightTicketDTO> getFlightTicketDtos(List<FlightTicket> flightTickets) {
        return flightTickets.stream()
                .map(flightTicket -> new FlightTicketDTO(
                        flightTicket.getId(),
                        flightTicket.getPassengerNationalId(),
                        flightTicket.getPassengerFirstName(),
                        flightTicket.getPassengerLastName(),
                        flightTicket.getTicketNumber(),
                        maskCC(flightTicket.getCreditCard()),
                        flightTicket.getIsActive()
                )).collect(Collectors.toList());
    }

    private String maskCC(String creditCard) {
        String fixCC = String.valueOf(creditCard);
        String privateCC = fixCC.replaceAll("[^\\d.]", "");

        return privateCC.substring(0, 6) + "******" + privateCC.substring(13);
    }
}
