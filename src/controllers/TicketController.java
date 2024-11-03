package controllers;

import dtos.GenerateTicketRequestDto;
import dtos.GenerateTicketResponseDto;
import dtos.ResponseStatus;
import models.Ticket;
import services.TicketService;

import java.util.List;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto){
        GenerateTicketResponseDto responseDto = new GenerateTicketResponseDto();
        try{
            Ticket ticket  = ticketService.generateTicket(requestDto.getGateId(), requestDto.getRegistrationNumber(),requestDto.getVehicleType(),requestDto.getAdditionalServices());
            responseDto.setTicket(ticket);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
