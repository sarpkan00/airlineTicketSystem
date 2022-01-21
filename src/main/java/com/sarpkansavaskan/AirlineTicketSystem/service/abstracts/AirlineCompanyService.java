package com.sarpkansavaskan.AirlineTicketSystem.service.abstracts;

import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineCompanyDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirlineCompanyRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirlineCompanyRequest;

import java.util.List;

public interface AirlineCompanyService {
    AirlineCompanyDTO add(CreateAirlineCompanyRequest request);
    AirlineCompanyDTO update(int id, UpdateAirlineCompanyRequest request);
    void deleteById(int id);
    AirlineCompanyDTO findByCompanyName(String companyName);
    AirlineCompanyDTO findById(int id);
    List<AirlineCompanyDTO> getAll();
}
