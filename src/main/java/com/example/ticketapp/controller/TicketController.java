package com.example.ticketapp.controller;

import com.example.ticketapp.model.Ticket;
import com.example.ticketapp.repository.TicketRepository;
import com.example.ticketapp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/buy")
    public String buyTicket(@RequestBody Ticket ticket) {
        double price = ticketService.calculatePrice(ticket.getType(), ticket.getQuantity());
        ticketRepository.save(ticket);
        return "Total price: " + price;
    }

    @GetMapping
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }
}
