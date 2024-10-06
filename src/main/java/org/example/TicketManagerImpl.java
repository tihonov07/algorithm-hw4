package org.example;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketManagerImpl implements TicketManager {

    private List<Ticket> tickets = new ArrayList<>();

    @Override
    public void add(Ticket ticket) {
        tickets.add(ticket);
        Collections.sort(tickets);
    }

    @Override
    public Ticket next() {
        return tickets.isEmpty() ? null : tickets.remove(0);
    }
}
