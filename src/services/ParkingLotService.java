package services;

import exceptions.InvalidParkingLotException;
import models.ParkingFloor;
import models.VehicleType;

import java.util.List;
import java.util.Map;

public interface ParkingLotService {

    // Do not modify the method signature, feel free to add more methods

    public Map<ParkingFloor, Map<String, Integer>> getParkingLotCapacity(long parkingLotId, List<Long> parkingFloors, List<VehicleType> vehicleTypes) throws InvalidParkingLotException;

}
