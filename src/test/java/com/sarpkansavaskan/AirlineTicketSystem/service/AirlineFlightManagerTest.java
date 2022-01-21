package com.sarpkansavaskan.AirlineTicketSystem.service;

import com.sarpkansavaskan.AirlineTicketSystem.TestSupport;
import com.sarpkansavaskan.AirlineTicketSystem.dto.AirlineFlightDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.converter.AirlineFlightDtoConverter;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateAirlineFlightRequest;
import com.sarpkansavaskan.AirlineTicketSystem.exception.GeneralNotFoundException;
import com.sarpkansavaskan.AirlineTicketSystem.service.conctres.AirlineCompanyManager;
import com.sarpkansavaskan.AirlineTicketSystem.service.conctres.AirlineFlightManager;
import com.sarpkansavaskan.AirlineTicketSystem.service.conctres.FlightRouteManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sarpkansavaskan.AirlineTicketSystem.model.AirlineFlight;
import com.sarpkansavaskan.AirlineTicketSystem.repository.AirlineFlightRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AirlineFlightManagerTest extends TestSupport {

    private AirlineFlightRepository airlineFlightRepository;
    private AirlineFlightDtoConverter airlineFlightDtoConverter;
    private AirlineFlightManager airlineFlightManager;
    private AirlineCompanyManager airlineCompanyManager;
    private FlightRouteManager flightRouteManager;

    @BeforeEach
    void setUp() {
        airlineFlightRepository = mock(AirlineFlightRepository.class);
        airlineFlightDtoConverter = mock(AirlineFlightDtoConverter.class);
        airlineCompanyManager = mock(AirlineCompanyManager.class);
        flightRouteManager = mock(FlightRouteManager.class);

        airlineFlightManager = new AirlineFlightManager(airlineFlightRepository, airlineFlightDtoConverter, airlineCompanyManager, flightRouteManager);
    }

    @Test
    void testAirlineFlightGetAll() {
        List<AirlineFlight> airlineCompanies = generateAirlineFlights();
        List<AirlineFlightDTO> airlineFlightDTOS = generateAirlineFlightDtos();

        when(airlineFlightRepository.findAll()).thenReturn(airlineCompanies);
        when(airlineFlightDtoConverter.convertToAirlineFlightDTOS(airlineCompanies)).thenReturn(airlineFlightDTOS);

        List<AirlineFlightDTO> result = airlineFlightManager.getAll();

        assertEquals(airlineFlightDTOS, result);

        verify(airlineFlightRepository).findAll();
        verify(airlineFlightDtoConverter).convertToAirlineFlightDTOS(airlineCompanies);
    }

    @Test
    void testAirlineFlightGetById_idNotExist() {

        when(airlineFlightRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airlineFlightManager.findById(1));

        verify(airlineFlightRepository).findById(1);
        verifyNoInteractions(airlineFlightDtoConverter);
    }

    @Test
    void testAirlineFlightGetById_idExist() {

        AirlineFlight airlineFlight = generateAirlineFlight();
        AirlineFlightDTO airlineFlightDTO = generateAirlineFlightDto();

        when(airlineFlightRepository.findById(1)).thenReturn(Optional.ofNullable(airlineFlight));
        when(airlineFlightDtoConverter.convert(Objects.requireNonNull(airlineFlight))).thenReturn(airlineFlightDTO);

        AirlineFlightDTO result = airlineFlightManager.findById(1);

        assertEquals(airlineFlightDTO, result);

        verify(airlineFlightRepository).findById(1);
        verify(airlineFlightDtoConverter).convert(airlineFlight);
    }

    @Test
    void testAirlineFlightUpdate_idNotExist() {
        UpdateAirlineFlightRequest request = generateUpdateAirlineFlightRequest();

        when(airlineFlightRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airlineFlightManager.updateById(1, request));

        verify(airlineFlightRepository).findById(1);
        verifyNoInteractions(airlineFlightDtoConverter);
    }

    @Test
    void testAirlineFlightUpdate_idExist() {
        UpdateAirlineFlightRequest request = generateUpdateAirlineFlightRequest();
        AirlineFlight airlineFlight = generateAirlineFlight();
        AirlineFlightDTO airlineFlightDTO = generateAirlineFlightDto();

        when(airlineFlightRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(airlineFlight));
        assert airlineFlight != null;
        when(airlineFlightRepository.save(airlineFlight)).thenReturn(airlineFlight);
        when(airlineFlightDtoConverter.convert(airlineFlight)).thenReturn(airlineFlightDTO);

        AirlineFlightDTO result = airlineFlightManager.updateById(1, request);

        assertEquals(airlineFlightDTO, result);

        verify(airlineFlightRepository).findById(1);
        verify(airlineFlightRepository).save(airlineFlight);
        verify(airlineFlightDtoConverter).convert(airlineFlight);
    }


    @Test
    void testAirlineFlightDelete_idExist() {
        AirlineFlight airlineFlight = generateAirlineFlight();
        when(airlineFlightRepository.findById(1)).thenReturn(Optional.ofNullable(airlineFlight));
        airlineFlightManager.deleteById(1);

        verify(airlineFlightRepository).deleteById(1);
    }

    @Test
    void testAirlineFlightFindFlightNumber_FlightNumberNotExist() {

        when(airlineFlightRepository.findByAirlineFlightNumber(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> airlineFlightManager.findByAirlineFlightNumber(1));

        verify(airlineFlightRepository).findByAirlineFlightNumber(1);
        verifyNoInteractions(airlineFlightDtoConverter);
    }

    @Test
    void testAirlineFlightFindFlightNumber_FlightNumberExist() {

        AirlineFlight airlineFlight = generateAirlineFlight();
        AirlineFlightDTO airlineFlightDTO = generateAirlineFlightDto();

        when(airlineFlightRepository.findByAirlineFlightNumber(1)).thenReturn(Optional.of(airlineFlight));
        when(airlineFlightDtoConverter.convert(airlineFlight)).thenReturn(airlineFlightDTO);

        AirlineFlightDTO result = airlineFlightManager.findByAirlineFlightNumber(1);

        assertEquals(airlineFlightDTO, result);

        verify(airlineFlightRepository).findByAirlineFlightNumber(1);
        verify(airlineFlightDtoConverter).convert(airlineFlight);
    }
}