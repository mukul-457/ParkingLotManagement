package services;


import exceptions.InvalidGateException;
import exceptions.TicketNotFoundException;
import models.Invoice;

public interface InvoiceService {
    public Invoice createInvoice(long ticketId, long gateId) throws TicketNotFoundException, InvalidGateException;
}