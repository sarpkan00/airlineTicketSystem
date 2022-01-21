package com.sarpkansavaskan.AirlineTicketSystem.service;

import com.sarpkansavaskan.AirlineTicketSystem.TestSupport;
import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineCompanyDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.converter.AirlineCompanyDtoConverter;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirlineCompanyRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirlineCompanyRequest;
import com.sarpkansavaskan.AirlineTicketSystem.exception.GeneralNotFoundException;
import com.sarpkansavaskan.AirlineTicketSystem.repository.AirlineCompanyRepository;
import com.sarpkansavaskan.AirlineTicketSystem.service.conctres.AirlineCompanyManager;
import com.sarpkansavaskan.AirlineTicketSystem.model.AirlineCompany;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class AirlineCompanyManagerTest extends TestSupport {

    private AirlineCompanyRepository airlineCompanyRepository;
    private AirlineCompanyDtoConverter airlineCompanyDtoConverter;
    private AirlineCompanyManager airlineCompanyManager;

    @BeforeEach
    void setUp() {
        airlineCompanyRepository = mock(AirlineCompanyRepository.class);
        airlineCompanyDtoConverter = mock(AirlineCompanyDtoConverter.class);

        airlineCompanyManager = new AirlineCompanyManager(airlineCompanyRepository, airlineCompanyDtoConverter);
    }

    @Test
    void testAirlineCompanyGetAll() {
        List<AirlineCompany> airlineCompanies = generateAirlineCompanies();
        List<AirlineCompanyDTO> airlineCompanyDTOS = generateAirlineCompanyDtos();

        when(airlineCompanyRepository.findAll()).thenReturn(airlineCompanies);
        when(airlineCompanyDtoConverter.convertToAirlineCompanyDTOS(airlineCompanies)).thenReturn(airlineCompanyDTOS);

        List<AirlineCompanyDTO> result = airlineCompanyManager.getAll();

        assertEquals(airlineCompanyDTOS, result);

        verify(airlineCompanyRepository).findAll();
        verify(airlineCompanyDtoConverter).convertToAirlineCompanyDTOS(airlineCompanies);
    }

    @Test
    void testAirlineCompanyGetById_idNotExist() {

        when(airlineCompanyRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airlineCompanyManager.findById(1));

        verify(airlineCompanyRepository).findById(1);
        verifyNoInteractions(airlineCompanyDtoConverter);
    }

    @Test
    void testAirlineCompanyGetById_idExist() {

        AirlineCompany airlineCompany = generateAirlineCompany();
        AirlineCompanyDTO airlineCompanyDTO = generateAirlineCompanyDto();

        when(airlineCompanyRepository.findById(1)).thenReturn(Optional.of(airlineCompany));
        when(airlineCompanyDtoConverter.convert(airlineCompany)).thenReturn(airlineCompanyDTO);

        AirlineCompanyDTO result = airlineCompanyManager.findById(1);

        assertEquals(airlineCompanyDTO, result);

        verify(airlineCompanyRepository).findById(1);
        verify(airlineCompanyDtoConverter).convert(airlineCompany);
    }

    @Test
    void testAirlineCompanyUpdate_idNotExist() {
        UpdateAirlineCompanyRequest request = generateUpdateAirlineCompanyRequest();

        when(airlineCompanyRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airlineCompanyManager.update(1, request));

        verify(airlineCompanyRepository).findById(1);
        verifyNoInteractions(airlineCompanyDtoConverter);
    }

    @Test
    void testAirlineCompanyUpdate_idExist() {
        UpdateAirlineCompanyRequest request = generateUpdateAirlineCompanyRequest();
        AirlineCompany airlineCompany = generateAirlineCompany();
        AirlineCompanyDTO airlineCompanyDTO = generateAirlineCompanyDto();

        when(airlineCompanyRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(airlineCompany));
        assert airlineCompany != null;
        when(airlineCompanyRepository.save(airlineCompany)).thenReturn(airlineCompany);
        when(airlineCompanyDtoConverter.convert(airlineCompany)).thenReturn(airlineCompanyDTO);

        AirlineCompanyDTO result = airlineCompanyManager.update(1, request);

        assertEquals(airlineCompanyDTO, result);

        verify(airlineCompanyRepository).findById(1);
        verify(airlineCompanyRepository).save(airlineCompany);
        verify(airlineCompanyDtoConverter).convert(airlineCompany);
    }

    @Test
    void testAirlineCompanyDelete_idExist() {
        AirlineCompany airlineCompany = generateAirlineCompany();
        when(airlineCompanyRepository.findById(1)).thenReturn(Optional.ofNullable(airlineCompany));
        airlineCompanyRepository.deleteById(1);

        verify(airlineCompanyRepository).deleteById(1);
    }

    @Test
    void testAirlineCompanyDelete_idNotExist() {
        when(airlineCompanyRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airlineCompanyManager.deleteById(1));

        verify(airlineCompanyRepository).findById(1);
        verifyNoInteractions(airlineCompanyDtoConverter);
    }

    @Test
    void testAirlineCompanyFindCompanyName_companyNameNotExist() {

        when(airlineCompanyRepository.findByCompanyName("companyName")).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airlineCompanyManager.findByCompanyName("companyName"));

        verify(airlineCompanyRepository).findByCompanyName("companyName");
        verifyNoInteractions(airlineCompanyDtoConverter);
    }

    @Test
    void testAirlineCompanyFindCompanyName_companyNameExist() {

        AirlineCompany airlineCompany = generateAirlineCompany();
        AirlineCompanyDTO airlineCompanyDTO = generateAirlineCompanyDto();

        when(airlineCompanyRepository.findByCompanyName("companyName")).thenReturn(Optional.of(airlineCompany));
        when(airlineCompanyDtoConverter.convert(airlineCompany)).thenReturn(airlineCompanyDTO);

        AirlineCompanyDTO result = airlineCompanyManager.findByCompanyName("companyName");

        assertEquals(airlineCompanyDTO, result);

        verify(airlineCompanyRepository).findByCompanyName("companyName");
        verify(airlineCompanyDtoConverter).convert(airlineCompany);
    }
}