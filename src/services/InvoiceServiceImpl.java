package services;
import exceptions.InvalidGateException;
import exceptions.TicketNotFoundException;
import models.*;
import repositories.*;

import strategies.pricing.PricingStrategy;
import strategies.pricing.PricingStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class InvoiceServiceImpl implements InvoiceService {
    private TicketRepository ticketRepo ;
    private GateRepository gateRepo;
    private PricingStrategy pricingStrategy;
    private PricingStrategyFactory pricingStrategyFactory;
    private InvoiceRepository invoiceRepo;

    public InvoiceServiceImpl(TicketRepository tr,PricingStrategyFactory pf, GateRepository gr, InvoiceRepository ir){
        this.gateRepo = gr;
        this.ticketRepo = tr;
        this.pricingStrategyFactory = pf;
        this.invoiceRepo = ir;
    }

    public Invoice createInvoice(long ticketId, long gateId) throws TicketNotFoundException, InvalidGateException {

        Optional<Ticket> ticketOpt = ticketRepo.getTicketById(ticketId);
        if (ticketOpt.isEmpty()){
            throw new TicketNotFoundException("Ticket not found");
        }
        Optional<Gate> gateOpt  = gateRepo.findById(gateId);
        if (gateOpt.isEmpty()){
            throw new InvalidGateException("There is no exit gate with given gate id");
        }

        Ticket ticket = ticketOpt.get();
        Gate exitGate = gateOpt.get();
        Date exiDate = new Date();
        pricingStrategy = pricingStrategyFactory.getPricingStrategy(exiDate);
        double parkingAmount = pricingStrategy.calculateAmount(ticket.getEntryTime(), exiDate, ticket.getVehicle().getVehicleType());
        double totalAmount = parkingAmount;
        if(ticket.getAdditionalServices().size() > 0){
            for(AdditionalService srv: ticket.getAdditionalServices()){
                totalAmount += srv.getCost();
            }
        }

        Invoice invoice  =  new Invoice();
        invoice.setAmount(totalAmount);
        invoice.setExitTime(exiDate);
        invoice.setGate(exitGate);
        invoice.setParkingAttendant(exitGate.getParkingAttendant());
        invoice.setTicket(ticket);
        invoiceRepo.save(invoice);
        return invoice;

    }
}
