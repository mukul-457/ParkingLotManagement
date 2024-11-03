package services;

import exceptions.*;
import models.Ticket;

import java.util.List;

public interface TicketService {
    // Do not modify the method signatures, feel free to add new methods
    public Ticket generateTicket(long gateId, String registrationNumber, String vehicleType, List<String> additionalServices) throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException, UnsupportedAdditionalService, AdditionalServiceNotSupportedByVehicle;
}