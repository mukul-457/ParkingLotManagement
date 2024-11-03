package repositories;

import models.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



public class TicketRepositoryImpl implements TicketRepository {
    private Map<Long, Ticket> ticketDb = new HashMap<>();

    public Ticket save(Ticket ticket){
        ticketDb.put(ticket.getId(), ticket);
        return ticket;
    }

    public Optional<Ticket> getTicketById(long ticketId){
        if (ticketDb.containsKey(ticketId)){
            return Optional.of(ticketDb.get(ticketId));
        }
        return Optional.empty();
    }
}