package com.sarpkansavaskan.AirlineTicketSystem.service.conctres;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineFlightDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.converter.AirlineFlightDtoConverter;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirlineFlightRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirlineFlightRequest;
import org.springframework.stereotype.Service;
import com.sarpkansavaskan.AirlineTicketSystem.model.AirlineFlight;
import com.sarpkansavaskan.AirlineTicketSystem.repository.AirlineFlightRepository;
import com.sarpkansavaskan.AirlineTicketSystem.service.abstracts.AirlineFlightService;
import com.sarpkansavaskan.AirlineTicketSystem.exception.GeneralNotFoundException;

import java.util.List;

@Service
public class AirlineFlightManager implements AirlineFlightService {

    private final AirlineFlightRepository airlineFlightRepository;
    private final AirlineFlightDtoConverter airlineFlightDtoConverter;
    private final AirlineCompanyManager airlineCompanyManager;
    private final FlightRouteManager flightRouteManager;

    public AirlineFlightManager(AirlineFlightRepository airlineFlightRepository, AirlineFlightDtoConverter airlineFlightDtoConverter, AirlineCompanyManager airlineCompanyManager, FlightRouteManager flightRouteManager) {
        this.airlineFlightRepository = airlineFlightRepository;
        this.airlineFlightDtoConverter = airlineFlightDtoConverter;
        this.airlineCompanyManager = airlineCompanyManager;
        this.flightRouteManager = flightRouteManager;
    }

    @Override
    public AirlineFlightDTO add(CreateAirlineFlightRequest request) {
        AirlineFlight airlineFlight = new AirlineFlight(
                request.getAirlineFlightNumber(),
                request.getPassengers(),
                request.getPrice(),
                request.getDate(),
                airlineCompanyManager.getById(request.getAirlineCompanyId()),
                flightRouteManager.getById(request.getFlightRouteId())
        );
        return airlineFlightDtoConverter.convert(airlineFlightRepository.save(airlineFlight));
    }

    @Override
    public AirlineFlightDTO updateById(int id, UpdateAirlineFlightRequest request) {
        AirlineFlight flight = getById(id);
        double passengerNumber = flight.getPassengers();
        double newPassengerNumber = flight.getPassengers();

        if(passengerNumber/10 <= newPassengerNumber-passengerNumber){
            flight.setPrice(flight.getPrice() + (flight.getPrice() / 10));
        }else{
            flight.setPrice(flight.getPrice());
        }
        flight.setAirlineCompany(flight.getAirlineCompany());
        flight.setFlightRoute(flight.getFlightRoute());
        flight.setPassengers(flight.getPassengers());
        flight.setDate(flight.getDate());
        flight.setAirlineFlightNumber(flight.getAirlineFlightNumber());

        return airlineFlightDtoConverter.convert(airlineFlightRepository.save(flight));
    }

    @Override
    public void deleteById(int id) {
        getById(id);
        airlineFlightRepository.deleteById(id);
    }

    @Override
    public AirlineFlightDTO findByAirlineFlightNumber(int airlineFlightNumber) {
        return airlineFlightDtoConverter.convert(airlineFlightRepository.findByAirlineFlightNumber(airlineFlightNumber).orElseThrow(() -> new GeneralNotFoundException(airlineFlightNumber + " Not Found")));
    }

    @Override
    public AirlineFlightDTO findById(int id) {
        return airlineFlightDtoConverter.convert(getById(id));
    }

    @Override
    public List<AirlineFlightDTO> getAll() {
        return airlineFlightDtoConverter.convertToAirlineFlightDTOS(airlineFlightRepository.findAll());
    }

    public AirlineFlight getById(int id) {
        return airlineFlightRepository.findById(id).orElseThrow(() -> new GeneralNotFoundException(id + " Not Found"));
    }
}
