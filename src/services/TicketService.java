package services;

import exceptions.InvalidGateException;
import exceptions.InvalidParkingLotException;
import exceptions.ParkingSpotNotAvailableException;
import models.Ticket;

public interface TicketService {
    // Do not modify the method signatures, feel free to add new methods
    public Ticket generateTicket(int gateId, String registrationNumber, String vehicleType) throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException;
}