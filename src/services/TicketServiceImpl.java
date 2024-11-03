package services;

import  java.util.Optional;
import  java.util.Date;
import  repositories.*;
import models.*;
import exceptions.*;
import strategies.assignment.SpotAssignmentStrategy;

public class TicketServiceImpl implements TicketService{

    private GateRepository gateRepo;
    private ParkingLotRepository parkingLotRepo;
    private VehicleRepository vehicleRepo ;
    private TicketRepository ticketRepo ;
    private SpotAssignmentStrategy strategy;

    public TicketServiceImpl(GateRepository gr, ParkingLotRepository pr, VehicleRepository vr, TicketRepository tr , SpotAssignmentStrategy stgy){
        this.gateRepo = gr;
        this.parkingLotRepo = pr;
        this.vehicleRepo = vr;
        this.ticketRepo  = tr;
        this.strategy = stgy;
    }

    public Ticket generateTicket(int gateId, String registrationNumber, String vehicleType) throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException{
        Gate gate = verifyGate(gateId);
        ParkingLot parkingLot =  verifyParkingLot(gateId);
        VehicleType type = getVehicleType(vehicleType);
        Vehicle vehicle  = addVehicle(registrationNumber , type);
        Optional<ParkingSpot> spotOpt = this.strategy.assignSpot(parkingLot, type);
        if (spotOpt.isEmpty()){
            throw new ParkingSpotNotAvailableException("Sorry ! we are fully occupied .");
        }
        ParkingSpot spot = spotOpt.get();
        spot.setStatus(ParkingSpotStatus.OCCUPIED);
        Ticket newTicket = new Ticket();
        newTicket.setGate(gate);
        newTicket.setVehicle(vehicle);
        newTicket.setParkingAttendant(gate.getParkingAttendant());
        newTicket.setParkingSpot(spot);
        newTicket.setEntryTime(new Date());
        ticketRepo.save(newTicket);
        return newTicket;
    }

    private Vehicle addVehicle(String registationNumber, VehicleType type){
        Vehicle vehicle;
        Optional<Vehicle> vechicleOpt  = vehicleRepo.getVehicleByRegistrationNumber(registationNumber);
        if (vechicleOpt.isEmpty()){
            vehicle = new Vehicle();
            vehicle.setRegistrationNumber(registationNumber);
            vehicle.setVehicleType(type);
            vehicleRepo.save(vehicle);
        }else{
            vehicle = vechicleOpt.get();
        }
        return vehicle;
    }

    private VehicleType getVehicleType(String type){
        switch (type){
            case "CAR":
                return VehicleType.CAR;
            case "BIKE":
                return VehicleType.BIKE;
            case "TRUCK":
                return VehicleType.TRUCK;
            case "SUV":
                return VehicleType.SUV;
            default:
                return VehicleType.CAR;
        }
    }

    private Gate verifyGate(int gateId) throws InvalidGateException{
        Optional<Gate> gateOpt  = gateRepo.findById(gateId);
        if (gateOpt.isEmpty() || gateOpt.get().getType() != GateType.ENTRY){
            throw new InvalidGateException("There is no entry gate with given Id");
        }
        return gateOpt.get();
    }

    private ParkingLot verifyParkingLot(long id) throws InvalidParkingLotException{
        Optional<ParkingLot> parkingLotOpt = parkingLotRepo.getParkingLotByGateId(id);
        if (parkingLotOpt.isEmpty()){
            throw new InvalidParkingLotException("There is no parking lot with this gate id");
        }
        return parkingLotOpt.get();
    }
}