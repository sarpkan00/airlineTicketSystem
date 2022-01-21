package com.sarpkansavaskan.AirlineTicketSystem.service.conctres;

import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightRouteDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.converter.FlightRouteDtoConverter;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateFlightRouteRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateFlightRouteRequest;
import org.springframework.stereotype.Service;
import com.sarpkansavaskan.AirlineTicketSystem.model.FlightRoute;
import com.sarpkansavaskan.AirlineTicketSystem.repository.FlightRouteRepository;
import com.sarpkansavaskan.AirlineTicketSystem.service.abstracts.FlightRouteService;
import com.sarpkansavaskan.AirlineTicketSystem.exception.GeneralNotFoundException;

import java.util.List;

@Service
public class FlightRouteManager implements FlightRouteService {

    private final FlightRouteRepository flightRouteRepository;
    private final FlightRouteDtoConverter flightRouteDtoConverter;

    public FlightRouteManager(FlightRouteRepository flightRouteRepository, FlightRouteDtoConverter flightRouteDtoConverter) {
        this.flightRouteRepository = flightRouteRepository;
        this.flightRouteDtoConverter = flightRouteDtoConverter;
    }

    @Override
    public FlightRouteDTO add(CreateFlightRouteRequest request) {
        FlightRoute flightRoute = new FlightRoute(
                request.getFrom(),
                request.getTo()
        );
        return flightRouteDtoConverter.convert(flightRouteRepository.save(flightRoute));
    }

    @Override
    public FlightRouteDTO updateById(int id, UpdateFlightRouteRequest request) {
        FlightRoute flightRoute = getById(id);
        flightRoute.setFrom(request.getFrom());
        flightRoute.setTo(request.getTo());

        return flightRouteDtoConverter.convert(flightRouteRepository.save(flightRoute));
    }

    @Override
    public void deleteById(int id) {
        getById(id);
        flightRouteRepository.deleteById(id);
    }

    @Override
    public FlightRouteDTO findByFrom(String from) {
        return flightRouteDtoConverter.convert(flightRouteRepository.findByFrom(from).orElseThrow(() -> new GeneralNotFoundException(from + " Not Found")));
    }

    @Override
    public FlightRouteDTO findByTo(String to) {
        return flightRouteDtoConverter.convert(flightRouteRepository.findByTo(to).orElseThrow(() -> new GeneralNotFoundException(to + " Not Found")));
    }

    @Override
    public FlightRouteDTO findById(int id) {
        return flightRouteDtoConverter.convert(getById(id));
    }

    @Override
    public List<FlightRouteDTO> getAll() {
        return flightRouteDtoConverter.convertToFlightRouteDTOS(flightRouteRepository.findAll());
    }

    protected FlightRoute getById(int id) {
        return flightRouteRepository.findById(id).orElseThrow(() -> new GeneralNotFoundException(id + " Not Found"));
    }
}
