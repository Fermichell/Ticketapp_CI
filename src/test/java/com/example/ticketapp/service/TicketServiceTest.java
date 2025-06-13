package com.example.ticketapp.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTest {

    private final TicketService ticketService = new TicketService();

    @Test
    void calculatePrice_whenTypeStandard_shouldReturnCorrectPrice() {
        //arrange
        String type = "standard";
        int quantity = 2;
        //act
        double result = ticketService.calculatePrice(type, quantity);
        //assert
        assertEquals(200, result);
    }

    @Test
    void calculatePrice_whenTypeVip_shouldReturnCorrectPrice() {
        String type = "vip";
        int quantity = 3;

        double result = ticketService.calculatePrice(type, quantity);

        assertEquals(600, result);
    }

    @Test
    void calculatePrice_whenTypeUnknown_shouldReturnDefaultPrice() {
        //arrange
        String type = "unknown";
        int quantity = 4;
        //act
        double result = ticketService.calculatePrice(type, quantity);
        //assert
        assertEquals(200, result);
    }
}