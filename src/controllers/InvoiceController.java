package controllers;

import dtos.GenerateInvoiceRequestDto;
import dtos.GenerateInvoiceResponseDto;
import dtos.ResponseStatus;
import exceptions.InvalidGateException;
import exceptions.TicketNotFoundException;
import models.Invoice;
import services.InvoiceService;

public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public GenerateInvoiceResponseDto createInvoice(GenerateInvoiceRequestDto requestDto){
        GenerateInvoiceResponseDto responseDto = new GenerateInvoiceResponseDto();
        try{
            Invoice invoice = invoiceService.createInvoice(requestDto.getTicketId(), requestDto.getGateId());
            responseDto.setInvoice(invoice);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        }catch(TicketNotFoundException | InvalidGateException e){
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}