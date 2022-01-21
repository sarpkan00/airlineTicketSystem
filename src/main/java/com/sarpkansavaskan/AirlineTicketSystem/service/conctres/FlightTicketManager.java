package com.sarpkansavaskan.AirlineTicketSystem.service.conctres;

import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightTicketDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.converter.FlightTicketDtoConverter;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateFlightTicketRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateFlightTicketRequest;
import com.sarpkansavaskan.AirlineTicketSystem.exception.GeneralNotFoundException;
import com.sarpkansavaskan.AirlineTicketSystem.model.enums.TicketEnum;
import org.springframework.stereotype.Service;
import com.sarpkansavaskan.AirlineTicketSystem.model.FlightTicket;
import com.sarpkansavaskan.AirlineTicketSystem.repository.FlightTicketRepository;
import com.sarpkansavaskan.AirlineTicketSystem.service.abstracts.FlightTicketService;

import java.util.List;

@Service
public class FlightTicketManager implements FlightTicketService {

    private final FlightTicketRepository flightTicketRepository;
    private final FlightTicketDtoConverter flightTicketDtoConverter;
    private final AirlineFlightManager airlineFlightManager;

    public FlightTicketManager(FlightTicketRepository flightTicketRepository, FlightTicketDtoConverter flightTicketDtoConverter, AirlineFlightManager airlineFlightManager) {
        this.flightTicketRepository = flightTicketRepository;
        this.flightTicketDtoConverter = flightTicketDtoConverter;
        this.airlineFlightManager = airlineFlightManager;
    }

    @Override
    public FlightTicketDTO buyTicket(CreateFlightTicketRequest request) {
        FlightTicket flightTicket = new FlightTicket(
                request.getPassengerNationalId(),
                request.getPassengerFirstName(),
                request.getPassengerLastName(),
                request.getTicketNumber(),
                request.getCreditCard(),
                airlineFlightManager.getById(request.getAirlineFlightId())
        );
        return flightTicketDtoConverter.convert(flightTicketRepository.save(flightTicket));
    }

    @Override
    public FlightTicketDTO updateById(int id, UpdateFlightTicketRequest request) {
        FlightTicket flightTicket = getById(id);
        flightTicket.setPassengerNationalId(request.getPassengerNationalId());
        flightTicket.setPassengerFirstName(request.getPassengerFirstName());
        flightTicket.setPassengerLastName(request.getPassengerLastName());
        flightTicket.setTicketNumber(request.getTicketNumber());
        flightTicket.setAirlineFlight(airlineFlightManager.getById(request.getAirlineFlightId()));

        return flightTicketDtoConverter.convert(flightTicketRepository.save(flightTicket));
    }

    @Override
    public FlightTicketDTO cancelTicket(int id) {
        FlightTicket flightTicket = getById(id);
        flightTicket.setIsActive(TicketEnum.CANCEL);

        return flightTicketDtoConverter.convert(flightTicketRepository.save(flightTicket));
    }

    @Override
    public FlightTicketDTO findByTicketNumber(long ticketNumber) {
        return flightTicketDtoConverter.convert(flightTicketRepository.findByTicketNumber(ticketNumber).orElseThrow(() -> new GeneralNotFoundException(ticketNumber + " Not Found")));
    }

    @Override
    public FlightTicketDTO findById(int id) {
        return flightTicketDtoConverter.convert(getById(id));
    }

    @Override
    public List<FlightTicketDTO> getAll() {
        return flightTicketDtoConverter.convertToFlightTicketDTOS(flightTicketRepository.findAll());
    }

    private FlightTicket getById(int id) {
        return flightTicketRepository.findById(id).orElseThrow(() -> new GeneralNotFoundException(id + " Not Found"));
    }
}
