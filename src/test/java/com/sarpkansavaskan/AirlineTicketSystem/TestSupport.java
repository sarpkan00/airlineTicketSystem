package com.sarpkansavaskan.AirlineTicketSystem;

import com.sarpkansavaskan.AirlineTicketSystem.dto.*;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.*;
import com.sarpkansavaskan.AirlineTicketSystem.model.*;
import com.sarpkansavaskan.AirlineTicketSystem.model.enums.TicketEnum;

import java.util.*;

public class TestSupport {

    Date date = new Date();
    public AirlineCompany generateAirlineCompany() {
        return new AirlineCompany(
                "companyName",
                "phoneNumber"
        );
    }

    public AirlineCompanyDTO generateAirlineCompanyDto() {
        List<AirlineFlightDTO> airlineFlightDTOS = new ArrayList<>();
        airlineFlightDTOS.add(generateAirlineFlightDto());
        return new AirlineCompanyDTO(
                1,
                "companyName",
                "phoneNumber",
                airlineFlightDTOS
        );
    }

    public UpdateAirlineCompanyRequest generateUpdateAirlineCompanyRequest() {
        return new UpdateAirlineCompanyRequest(
                "companyName",
                "phoneNumber"
        );
    }

    public List<AirlineCompany> generateAirlineCompanies() {
        List<AirlineCompany> airlineCompanies = new ArrayList<>();
        airlineCompanies.add(generateAirlineCompany());
        return airlineCompanies;
    }

    public List<AirlineCompanyDTO> generateAirlineCompanyDtos() {
        List<AirlineCompanyDTO> airlineCompanyDtos = new ArrayList<>();
        airlineCompanyDtos.add(generateAirlineCompanyDto());
        return airlineCompanyDtos;
    }

    public FlightRoute generateFlightRoute() {
        return new FlightRoute(
                "from",
                "to"
        );
    }

    public FlightTicketDTO generateFlightTicketDto() {
        return new FlightTicketDTO(
                1,
                1,
                "passengerFirstName",
                "passengerLastName",
                1,
                "1234567891123456",
                TicketEnum.ACTIVE
        );
    }

    public FlightTicketDTO generateFlightTicketCancelDto() {
        return new FlightTicketDTO(
                1,
                1,
                "passengerFirstName",
                "passengerLastName",
                1,
                "1234567891123456",
                TicketEnum.CANCEL
        );
    }

    public List<FlightTicketDTO> generateFlightTicketDtos() {
        List<FlightTicketDTO> flightTicketDTOS = new ArrayList<>();
        flightTicketDTOS.add(generateFlightTicketDto());
        return flightTicketDTOS;
    }


    public AirlineFlight generateAirlineFlight() {
        return new AirlineFlight(
                1,
                1,
                1.0,
                date
        );
    }

    public AirlineFlightDTO generateAirlineFlightDto() {
        return new AirlineFlightDTO(
                1,
                1,
                1,
                1.0,
                date,
                "companyName",
                1,
                generateFlightTicketDtos()
        );
    }

  public UpdateAirlineFlightRequest generateUpdateAirlineFlightRequest() {
        return new UpdateAirlineFlightRequest(
                1,
                1.0
        );
    }

    public List<AirlineFlight> generateAirlineFlights() {
        List<AirlineFlight> airlineFlights = new ArrayList<>();
        airlineFlights.add(generateAirlineFlight());
        return airlineFlights;
    }

    public List<AirlineFlightDTO> generateAirlineFlightDtos() {
        List<AirlineFlightDTO> airlineFlightDTOS = new ArrayList<>();
        airlineFlightDTOS.add(generateAirlineFlightDto());
        return airlineFlightDTOS;
    }

    public UpdateAirportRequest generateUpdateAirportRequest() {
        return new UpdateAirportRequest(
                "airportName"
        );
    }

    public Airport generateAirport() {
        return new Airport(
                "airportName"
        );
    }

    public AirportDTO generateAirportDto() {
        return new AirportDTO(
                1,
                "airportName"
        );
    }

    public List<Airport> generateAirports() {
        List<Airport> airports = new ArrayList<>();
        airports.add(generateAirport());
        return airports;
    }

    public List<AirportDTO> generateAirportDtos() {
        List<AirportDTO> airports = new ArrayList<>();
        airports.add(generateAirportDto());
        return airports;
    }

    public UpdateFlightRouteRequest generateUpdateFlightRouteRequest() {
        return new UpdateFlightRouteRequest(
                "İstanbul",
                "Ankara"
        );
    }

    public FlightRouteDTO generateFlightRouteDto() {
        return new FlightRouteDTO(
                1,
                "İstanbul",
                "Ankara",
                generateAirlineFlightDtos()
        );
    }

    public List<FlightRoute> generateFlightRoutes() {
        List<FlightRoute> flightRoutes = new ArrayList<>();
        flightRoutes.add(generateFlightRoute());
        return flightRoutes;
    }

    public List<FlightRouteDTO> generateFlightRouteDtos() {
        List<FlightRouteDTO> flightRouteDtos = new ArrayList<>();
        flightRouteDtos.add(generateFlightRouteDto());
        return flightRouteDtos;
    }

    public UpdateFlightTicketRequest generateUpdateFlightTicketRequest() {
        return new UpdateFlightTicketRequest(
                123,
                "passengerFirstName",
                "passengerLastName",
                123,
                123
        );
    }

    public FlightTicket generateFlightTicket() {
        return new FlightTicket(
                1,
                123,
                "passengerFirstName",
                "passengerLastName",
                123,
                "creditCard",
                generateAirlineFlight(),
                TicketEnum.ACTIVE
        );
    }

    public List<FlightTicket> generateFlightTickets() {
        List<FlightTicket> flightTickets = new ArrayList<>();
        flightTickets.add(generateFlightTicket());
        return flightTickets;
    }
}
