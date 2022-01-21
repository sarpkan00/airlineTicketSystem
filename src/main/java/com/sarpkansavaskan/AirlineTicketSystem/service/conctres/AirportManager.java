package com.sarpkansavaskan.AirlineTicketSystem.service.conctres;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirportDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.converter.AirportDtoConverter;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirportRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirportRequest;
import org.springframework.stereotype.Service;
import com.sarpkansavaskan.AirlineTicketSystem.model.Airport;
import com.sarpkansavaskan.AirlineTicketSystem.repository.AirportRepository;
import com.sarpkansavaskan.AirlineTicketSystem.service.abstracts.AirportService;
import com.sarpkansavaskan.AirlineTicketSystem.exception.GeneralNotFoundException;

import java.util.List;

@Service
public class AirportManager implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportDtoConverter airportDtoConverter;

    public AirportManager(AirportRepository airportRepository, AirportDtoConverter airportDtoConverter) {
        this.airportRepository = airportRepository;
        this.airportDtoConverter = airportDtoConverter;
    }

    @Override
    public AirportDTO add(CreateAirportRequest request) {
        Airport airport = new Airport(
                request.getAirportName()
        );
        return airportDtoConverter.convert(airportRepository.save(airport));
    }

    @Override
    public AirportDTO updateById(int id, UpdateAirportRequest request) {

        Airport airport1 = getById(id);
        airport1.setAirportName(request.getAirportName());

        return airportDtoConverter.convert(airportRepository.save(airport1));
    }

    @Override
    public void deleteById(int id) {
        getById(id);
        airportRepository.deleteById(id);
    }

    @Override
    public AirportDTO findByAirportName(String airportName) {
        return airportDtoConverter.convert(airportRepository.findByAirportName(airportName).orElseThrow(() -> new GeneralNotFoundException(airportName + " Not Found")));
    }

    @Override
    public AirportDTO findById(int id) {
        return airportDtoConverter.convert(getById(id));
    }

    @Override
    public List<AirportDTO> getAll() {
        return airportDtoConverter.convertToAirportDTOS(airportRepository.findAll());
    }

    protected Airport getById(int id) {
        return airportRepository.findById(id).orElseThrow(() -> new GeneralNotFoundException(id + " Not Found"));
    }
}
