package com.sarpkansavaskan.AirlineTicketSystem.service;

import com.sarpkansavaskan.AirlineTicketSystem.TestSupport;
import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightRouteDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.converter.FlightRouteDtoConverter;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateFlightRouteRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateFlightRouteRequest;
import com.sarpkansavaskan.AirlineTicketSystem.exception.GeneralNotFoundException;

import com.sarpkansavaskan.AirlineTicketSystem.service.conctres.FlightRouteManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sarpkansavaskan.AirlineTicketSystem.model.FlightRoute;
import com.sarpkansavaskan.AirlineTicketSystem.repository.FlightRouteRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class FlightRouteManagerTest extends TestSupport {
    private FlightRouteRepository flightRouteRepository;
    private FlightRouteDtoConverter flightRouteDtoConverter;
    private FlightRouteManager flightRouteManager;

    @BeforeEach
    void setUp() {
        flightRouteRepository = mock(FlightRouteRepository.class);
        flightRouteDtoConverter = mock(FlightRouteDtoConverter.class);

        flightRouteManager = new FlightRouteManager(flightRouteRepository, flightRouteDtoConverter);
    }

    @Test
    void testFlightRouteGetAll() {
        List<FlightRoute> flightRoutes = generateFlightRoutes();
        List<FlightRouteDTO> flightRouteDTOS = generateFlightRouteDtos();

        when(flightRouteRepository.findAll()).thenReturn(flightRoutes);
        when(flightRouteDtoConverter.convertToFlightRouteDTOS(flightRoutes)).thenReturn(flightRouteDTOS);

        List<FlightRouteDTO> result = flightRouteManager.getAll();

        assertEquals(flightRouteDTOS, result);

        verify(flightRouteRepository).findAll();
        verify(flightRouteDtoConverter).convertToFlightRouteDTOS(flightRoutes);
    }

    @Test
    void testFlightRouteGetById_idNotExist() {

        when(flightRouteRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> flightRouteManager.findById(1));

        verify(flightRouteRepository).findById(1);
        verifyNoInteractions(flightRouteDtoConverter);
    }

    @Test
    void testFlightRouteGetById_idExist() {

        FlightRoute flightRoute = generateFlightRoute();
        FlightRouteDTO flightRouteDTO = generateFlightRouteDto();

        when(flightRouteRepository.findById(1)).thenReturn(Optional.of(flightRoute));
        when(flightRouteDtoConverter.convert(flightRoute)).thenReturn(flightRouteDTO);

        FlightRouteDTO result = flightRouteManager.findById(1);

        assertEquals(flightRouteDTO, result);

        verify(flightRouteRepository).findById(1);
        verify(flightRouteDtoConverter).convert(flightRoute);
    }

    @Test
    void testFlightRouteUpdate_idNotExist() {
        UpdateFlightRouteRequest request = generateUpdateFlightRouteRequest();

        when(flightRouteRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> flightRouteManager.updateById(1, request));

        verify(flightRouteRepository).findById(1);
        verifyNoInteractions(flightRouteDtoConverter);
    }

    @Test
    void testFlightRouteUpdate_idExist() {
        UpdateFlightRouteRequest request = generateUpdateFlightRouteRequest();
        FlightRoute flightRoute = generateFlightRoute();
        FlightRouteDTO flightRouteDTO = generateFlightRouteDto();

        when(flightRouteRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(flightRoute));
        assert flightRoute != null;
        when(flightRouteRepository.save(flightRoute)).thenReturn(flightRoute);
        when(flightRouteDtoConverter.convert(flightRoute)).thenReturn(flightRouteDTO);

        FlightRouteDTO result = flightRouteManager.updateById(1, request);

        assertEquals(flightRouteDTO, result);

        verify(flightRouteRepository).findById(1);
        verify(flightRouteRepository).save(flightRoute);
        verify(flightRouteDtoConverter).convert(flightRoute);
    }


    @Test
    void testFlightRouteDelete_idExist() {
        FlightRoute flightRoute = generateFlightRoute();
        when(flightRouteRepository.findById(1)).thenReturn(Optional.ofNullable(flightRoute));
        flightRouteRepository.deleteById(1);

        verify(flightRouteRepository).deleteById(1);
    }

    @Test
    void testFlightRouteDelete_idNotExist() {
        when(flightRouteRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> flightRouteManager.deleteById(1));

        verify(flightRouteRepository).findById(1);
        verifyNoInteractions(flightRouteDtoConverter);
    }

    @Test
    void testFlightRouteFindFrom_fromNotExist() {

        when(flightRouteRepository.findByFrom("From")).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> flightRouteManager.findByFrom("From"));

        verify(flightRouteRepository).findByFrom("From");
        verifyNoInteractions(flightRouteDtoConverter);
    }

    @Test
    void testFlightRouteFindFrom_fromExist() {

        FlightRoute flightRoute = generateFlightRoute();
        FlightRouteDTO flightRouteDTO = generateFlightRouteDto();

        when(flightRouteRepository.findByFrom("From")).thenReturn(Optional.of(flightRoute));
        when(flightRouteDtoConverter.convert(flightRoute)).thenReturn(flightRouteDTO);

        FlightRouteDTO result = flightRouteManager.findByFrom("From");

        assertEquals(flightRouteDTO, result);

        verify(flightRouteRepository).findByFrom("From");
        verify(flightRouteDtoConverter).convert(flightRoute);
    }

    @Test
    void testFlightRouteFindTo_toNotExist() {

        when(flightRouteRepository.findByTo("To")).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> flightRouteManager.findByTo("To"));

        verify(flightRouteRepository).findByTo("To");
        verifyNoInteractions(flightRouteDtoConverter);
    }

    @Test
    void testFlightRouteFindTo_toExist() {

        FlightRoute flightRoute = generateFlightRoute();
        FlightRouteDTO flightRouteDTO = generateFlightRouteDto();

        when(flightRouteRepository.findByTo("To")).thenReturn(Optional.of(flightRoute));
        when(flightRouteDtoConverter.convert(flightRoute)).thenReturn(flightRouteDTO);

        FlightRouteDTO result = flightRouteManager.findByTo("To");

        assertEquals(flightRouteDTO, result);

        verify(flightRouteRepository).findByTo("To");
        verify(flightRouteDtoConverter).convert(flightRoute);
    }


}
