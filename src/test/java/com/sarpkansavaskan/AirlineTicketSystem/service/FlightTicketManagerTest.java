package com.sarpkansavaskan.AirlineTicketSystem.service;

import com.sarpkansavaskan.AirlineTicketSystem.TestSupport;
import com.sarpkansavaskan.AirlineTicketSystem.dto.FlightTicketDTO;
import com.sarpkansavaskan.AirlineTicketSystem.dto.converter.FlightTicketDtoConverter;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.CreateFlightTicketRequest;
import com.sarpkansavaskan.AirlineTicketSystem.dto.request.UpdateFlightTicketRequest;
import com.sarpkansavaskan.AirlineTicketSystem.exception.GeneralNotFoundException;
import com.sarpkansavaskan.AirlineTicketSystem.model.FlightTicket;
import com.sarpkansavaskan.AirlineTicketSystem.model.enums.TicketEnum;
import com.sarpkansavaskan.AirlineTicketSystem.repository.FlightTicketRepository;
import com.sarpkansavaskan.AirlineTicketSystem.service.conctres.AirlineFlightManager;
import com.sarpkansavaskan.AirlineTicketSystem.service.conctres.FlightTicketManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightTicketManagerTest extends TestSupport {

    private FlightTicketRepository flightTicketRepository;
    private FlightTicketDtoConverter flightTicketDtoConverter;
    private AirlineFlightManager airlineFlightManager;
    private FlightTicketManager flightTicketManager;

    @BeforeEach
    void setUp(){
        flightTicketRepository = mock(FlightTicketRepository.class);
        flightTicketDtoConverter = mock(FlightTicketDtoConverter.class);
        airlineFlightManager = mock(AirlineFlightManager.class);

        flightTicketManager = new FlightTicketManager(flightTicketRepository, flightTicketDtoConverter, airlineFlightManager);
    }

    @Test
    void testFlightTicketGetAll() {
        List<FlightTicket> flightTickets = generateFlightTickets();
        List<FlightTicketDTO> flightTicketDTOS = generateFlightTicketDtos();

        when(flightTicketRepository.findAll()).thenReturn(flightTickets);
        when(flightTicketDtoConverter.convertToFlightTicketDTOS(flightTickets)).thenReturn(flightTicketDTOS);

        List<FlightTicketDTO> result = flightTicketManager.getAll();

        assertEquals(flightTicketDTOS, result);

        verify(flightTicketRepository).findAll();
        verify(flightTicketDtoConverter).convertToFlightTicketDTOS(flightTickets);
    }

    @Test
    void testFlightTicketGetById_idNotExist() {

        when(flightTicketRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> flightTicketManager.findById(1));

        verify(flightTicketRepository).findById(1);
        verifyNoInteractions(flightTicketDtoConverter);
    }

    @Test
    void testFlightTicketGetById_idExist() {

        FlightTicket flightTicket = generateFlightTicket();
        FlightTicketDTO flightTicketDTO = generateFlightTicketDto();

        when(flightTicketRepository.findById(1)).thenReturn(Optional.of(flightTicket));
        when(flightTicketDtoConverter.convert(flightTicket)).thenReturn(flightTicketDTO);

        FlightTicketDTO result = flightTicketManager.findById(1);

        assertEquals(flightTicketDTO, result);

        verify(flightTicketRepository).findById(1);
        verify(flightTicketDtoConverter).convert(flightTicket);
    }

    @Test
    void testFlightTicketUpdate_idNotExist() {
        UpdateFlightTicketRequest request = generateUpdateFlightTicketRequest();

        when(flightTicketRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> flightTicketManager.updateById(1, request));

        verify(flightTicketRepository).findById(1);
        verifyNoInteractions(flightTicketDtoConverter);
    }

    @Test
    void testFlightTicketUpdate_idExist() {
        UpdateFlightTicketRequest request = generateUpdateFlightTicketRequest();
        FlightTicket flightTicket = generateFlightTicket();
        FlightTicketDTO flightTicketDTO = generateFlightTicketDto();

        when(flightTicketRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(flightTicket));
        assert flightTicket != null;
        when(flightTicketRepository.save(flightTicket)).thenReturn(flightTicket);
        when(flightTicketDtoConverter.convert(flightTicket)).thenReturn(flightTicketDTO);

        FlightTicketDTO result = flightTicketManager.updateById(1, request);

        assertEquals(flightTicketDTO, result);

        verify(flightTicketRepository).findById(1);
        verify(flightTicketRepository).save(flightTicket);
        verify(flightTicketDtoConverter).convert(flightTicket);
    }

    @Test
    void testFlightTicketDelete_idExist() {
        FlightTicket flightTicket = generateFlightTicket();
        FlightTicketDTO flightTicketDTO = generateFlightTicketCancelDto();
        when(flightTicketRepository.findById(1)).thenReturn(Optional.ofNullable(flightTicket));
        when(flightTicketRepository.save(Objects.requireNonNull(flightTicket))).thenReturn(flightTicket);
        when(flightTicketDtoConverter.convert(flightTicket)).thenReturn(flightTicketDTO);

        FlightTicketDTO result = flightTicketManager.cancelTicket(1);

        assertEquals(flightTicketDTO, result);

        verify(flightTicketRepository).findById(1);
        verify(flightTicketRepository).save(flightTicket);
        verify(flightTicketDtoConverter).convert(flightTicket);
    }

    @Test
    void testFlightTicketDelete_idNotExist() {
        when(flightTicketRepository.findById(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> flightTicketManager.cancelTicket(1));

        verify(flightTicketRepository).findById(1);
        verifyNoInteractions(flightTicketDtoConverter);
    }

    @Test
    void testFlightTicketFindTicketNumber_ticketNumberNotExist() {

        when(flightTicketRepository.findByTicketNumber(1)).thenThrow(GeneralNotFoundException.class);

        assertThrows(GeneralNotFoundException.class, () -> flightTicketManager.findByTicketNumber(1));

        verify(flightTicketRepository).findByTicketNumber(1);
        verifyNoInteractions(flightTicketDtoConverter);
    }

    @Test
    void testFlightTicketFindTicketNumber_ticketNumberExist() {

        FlightTicket flightTicket = generateFlightTicket();
        FlightTicketDTO flightTicketDTO = generateFlightTicketDto();

        when(flightTicketRepository.findByTicketNumber(1)).thenReturn(Optional.of(flightTicket));
        when(flightTicketDtoConverter.convert(flightTicket)).thenReturn(flightTicketDTO);

        FlightTicketDTO result = flightTicketManager.findByTicketNumber(1);

        assertEquals(flightTicketDTO, result);

        verify(flightTicketRepository).findByTicketNumber(1);
        verify(flightTicketDtoConverter).convert(flightTicket);
    }

}