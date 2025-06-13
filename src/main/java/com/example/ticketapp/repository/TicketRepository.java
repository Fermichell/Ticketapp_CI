package com.example.ticketapp.repository;

import com.example.ticketapp.model.Ticket;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {

    private final List<Ticket> tickets = new ArrayList<>();

    public void save(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> findAll() {
        return tickets;
    }
}
