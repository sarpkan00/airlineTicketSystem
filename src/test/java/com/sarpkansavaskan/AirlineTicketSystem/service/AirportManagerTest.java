package com.sarpkansavaskan.AirlineTicketSystem.service;

import com.sarpkansavaskan.AirlineTicketSystem.TestSupport;
import com.sarpkansavaskan.AirlineTicketSystem.dto.AirportDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.converter.AirportDtoConverter;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateAirportRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirportRequest;
import com.sarpkansavaskan.AirlineTicketSystem.exception.GeneralNotFoundException;
import com.sarpkansavaskan.AirlineTicketSystem.model.Airport;
import com.sarpkansavaskan.AirlineTicketSystem.repository.AirportRepository;
import com.sarpkansavaskan.AirlineTicketSystem.service.conctres.AirportManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirportManagerTest extends TestSupport {

    private AirportRepository airportRepository;
    private AirportDtoConverter airportDtoConverter;
    private AirportManager airportManager;

    @BeforeEach
    void setUp() {
        airportRepository = mock(AirportRepository.class);
        airportDtoConverter = mock(AirportDtoConverter.class);

        airportManager = new AirportManager(airportRepository, airportDtoConverter);
    }

    @Test
    void testAirportGetAll() {
        List<Airport> airports = generateAirports();
        List<AirportDTO> airportDTOS = generateAirportDtos();

        when(airportRepository.findAll()).thenReturn(airports);
        when(airportDtoConverter.convertToAirportDTOS(airports)).thenReturn(airportDTOS);

        List<AirportDTO> result = airportManager.getAll();

        assertEquals(airportDTOS, result);

        verify(airportRepository).findAll();
        verify(airportDtoConverter).convertToAirportDTOS(airports);
    }

    @Test
    void testAirportGetById_idNotExist() {

        when(airportRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airportManager.findById(1));

        verify(airportRepository).findById(1);
        verifyNoInteractions(airportDtoConverter);
    }

    @Test
    void testAirportGetById_idExist() {

        Airport airport = generateAirport();
        AirportDTO airportDTO = generateAirportDto();

        when(airportRepository.findById(1)).thenReturn(Optional.of(airport));
        when(airportDtoConverter.convert(airport)).thenReturn(airportDTO);

        AirportDTO result = airportManager.findById(1);

        assertEquals(airportDTO, result);

        verify(airportRepository).findById(1);
        verify(airportDtoConverter).convert(airport);
    }

    @Test
    void testAirportUpdate_idNotExist() {
        UpdateAirportRequest request = generateUpdateAirportRequest();

        when(airportRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airportManager.updateById(1, request));

        verify(airportRepository).findById(1);
        verifyNoInteractions(airportDtoConverter);
    }

    @Test
    void testAirportUpdate_idExist() {
        UpdateAirportRequest request = generateUpdateAirportRequest();
        Airport airport = generateAirport();
        AirportDTO airportDTO = generateAirportDto();

        when(airportRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(airport));
        assert airport != null;
        when(airportRepository.save(airport)).thenReturn(airport);
        when(airportDtoConverter.convert(airport)).thenReturn(airportDTO);

        AirportDTO result = airportManager.updateById(1, request);

        assertEquals(airportDTO, result);

        verify(airportRepository).findById(1);
        verify(airportRepository).save(airport);
        verify(airportDtoConverter).convert(airport);
    }

    @Test
    void testAirportDelete_idExist() {
        Airport airport = generateAirport();
        when(airportRepository.findById(1)).thenReturn(Optional.ofNullable(airport));
        airportManager.deleteById(1);

        verify(airportRepository).deleteById(1);
    }

    @Test
    void testAAirportDelete_idNotExist() {
        when(airportRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airportManager.deleteById(1));

        verify(airportRepository).findById(1);
        verifyNoInteractions(airportDtoConverter);
    }

    @Test
    void testAirportFindAirportName_airportNameNotExist() {

        when(airportRepository.findByAirportName("airportName")).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airportManager.findByAirportName("airportName"));

        verify(airportRepository).findByAirportName("airportName");
        verifyNoInteractions(airportDtoConverter);
    }

    @Test
    void testAirportFindAirportName_airportNameExist() {

        Airport airport = generateAirport();
        AirportDTO airportDTO = generateAirportDto();

        when(airportRepository.findByAirportName("airportName")).thenReturn(Optional.of(airport));
        when(airportDtoConverter.convert(airport)).thenReturn(airportDTO);

        AirportDTO result = airportManager.findByAirportName("airportName");

        assertEquals(airportDTO, result);

        verify(airportRepository).findByAirportName("airportName");
        verify(airportDtoConverter).convert(airport);
    }
}