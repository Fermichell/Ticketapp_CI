package com.example.ticketapp.controller;


import com.example.ticketapp.model.Ticket;
import com.example.ticketapp.repository.TicketRepository;
import com.example.ticketapp.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketController ticketController;

    @Test
    void buyTicket_shouldCallSaveOnTicketRepository() {
        Ticket ticket = new Ticket("standard", 2);
        when(ticketService.calculatePrice("standard", 2)).thenReturn(200.0);
        String result = ticketController.buyTicket(ticket);
        verify(ticketRepository, times(1)).save(ticket);
        verify(ticketService, times(1)).calculatePrice("standard", 2);
    }
}