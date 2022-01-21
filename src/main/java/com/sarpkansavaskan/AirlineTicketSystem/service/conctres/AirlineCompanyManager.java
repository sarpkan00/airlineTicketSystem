package com.sarpkansavaskan.AirlineTicketSystem.service.conctres;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineCompanyDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.converter.AirlineCompanyDtoConverter;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirlineCompanyRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirlineCompanyRequest;
import org.springframework.stereotype.Service;
import com.sarpkansavaskan.AirlineTicketSystem.model.AirlineCompany;
import com.sarpkansavaskan.AirlineTicketSystem.repository.AirlineCompanyRepository;
import com.sarpkansavaskan.AirlineTicketSystem.service.abstracts.AirlineCompanyService;
import com.sarpkansavaskan.AirlineTicketSystem.exception.GeneralNotFoundException;

import java.util.List;

@Service
public class AirlineCompanyManager implements AirlineCompanyService {

    private final AirlineCompanyRepository airlineCompanyRepository;
    private final AirlineCompanyDtoConverter airlineCompanyDtoConverter;

    public AirlineCompanyManager(AirlineCompanyRepository airlineCompanyRepository, AirlineCompanyDtoConverter airlineCompanyDtoConverter) {
        this.airlineCompanyRepository = airlineCompanyRepository;
        this.airlineCompanyDtoConverter = airlineCompanyDtoConverter;
    }

    @Override
    public AirlineCompanyDTO add(CreateAirlineCompanyRequest request) {
        AirlineCompany airlineCompany = new AirlineCompany(
                request.getCompanyName(),
                request.getPhoneNumber()
        );
        return airlineCompanyDtoConverter.convert(airlineCompanyRepository.save(airlineCompany));
    }

    @Override
    public AirlineCompanyDTO update(int id, UpdateAirlineCompanyRequest request) {
        AirlineCompany airlineCompany = getById(id);
        airlineCompany.setCompanyName(request.getCompanyName());
        airlineCompany.setPhoneNumber(request.getPhoneNumber());
        return airlineCompanyDtoConverter.convert(airlineCompanyRepository.save(airlineCompany));
    }

    @Override
    public void deleteById(int id) {
        getById(id);
        airlineCompanyRepository.deleteById(id);
    }

    @Override
    public AirlineCompanyDTO findByCompanyName(String companyName) {
        return airlineCompanyDtoConverter.convert(getByCompanyName(companyName));
    }

    @Override
    public AirlineCompanyDTO findById(int id) {

        return airlineCompanyDtoConverter.convert(getById(id));
    }

    @Override
    public List<AirlineCompanyDTO> getAll() {
        return airlineCompanyDtoConverter.convertToAirlineCompanyDTOS(airlineCompanyRepository.findAll());
    }

    protected AirlineCompany getById(int id) {
        return airlineCompanyRepository.findById(id).orElseThrow(() -> new GeneralNotFoundException(id + " Not Found"));
    }

    protected AirlineCompany getByCompanyName(String companyName) {
        return airlineCompanyRepository.findByCompanyName(companyName).orElseThrow(() -> new GeneralNotFoundException(companyName + " Not Found"));
    }
}
