package com.sarpkansavaskan.AirlineTicketSystem.dto.converter;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineCompanyDTO;
import com.sarpkansavaskan.AirlineTicketSystem.model.AirlineCompany;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirlineCompanyDtoConverter extends BaseDtoConverter {

    public AirlineCompanyDTO convert(AirlineCompany airlineCompany) {
        return new AirlineCompanyDTO(
                airlineCompany.getId(),
                airlineCompany.getCompanyName(),
                airlineCompany.getPhoneNumber(),
                getAirlineFlightDtos((airlineCompany.getAirlineFlights()))
        );
    }

    public List<AirlineCompanyDTO> convertToAirlineCompanyDTOS(List<AirlineCompany> airlineCompanies) {
        return airlineCompanies
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
