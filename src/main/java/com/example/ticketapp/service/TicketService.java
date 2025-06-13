package com.example.ticketapp.service;


import org.springframework.stereotype.Service;

@Service
public class TicketService {

    public double calculatePrice(String type, int quantity) {
        double pricePerUnit = switch (type) {
            case "standard" -> 100;
            case "vip" -> 200;
            default -> 50;
        };
        return pricePerUnit * quantity;
    }
}
