package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import models.*;
import repositories.*;
import exceptions.*;

public class ParkingLotServiceImpl implements ParkingLotService{
    private ParkingLotRepository parkingLotRepo;

    public ParkingLotServiceImpl(ParkingLotRepository pr){
        this.parkingLotRepo = pr;
    }

    public Map<ParkingFloor, Map<String, Integer>> getParkingLotCapacity(long parkingLotId, List<Long> parkingFloors, List<VehicleType> vehicleTypes) throws InvalidParkingLotException{
        Optional<ParkingLot> parkingLotOpt  = parkingLotRepo.getParkingLotById(parkingLotId);
        if (parkingLotOpt.isEmpty()){
            throw new InvalidParkingLotException("");
        }
        ParkingLot parkingLot = parkingLotOpt.get();
        List<ParkingFloor> allParkingFloors = parkingLot.getParkingFloors();
        if (parkingFloors != null){
            allParkingFloors = allParkingFloors.stream().filter(pf -> parkingFloors.contains(pf.getId())).toList();
        }
        Map<ParkingFloor , Map<String , Integer>> capacity  = new HashMap<>();
        for (ParkingFloor pf : allParkingFloors){
            Map<String , Integer> floorCapacity  = new HashMap<>();
            Integer bikeCapacity = 0;
            Integer carCapacity = 0;
            Integer TruckCapacity = 0;
            for(ParkingSpot sp : pf.getSpots()){
                if (sp.getStatus() == ParkingSpotStatus.AVAILABLE){
                    if (sp.getSupportedVehicleType() == VehicleType.BIKE){
                        bikeCapacity++;
                    }else if(sp.getSupportedVehicleType() == VehicleType.CAR){
                        carCapacity++;
                    }else{
                        TruckCapacity++;
                    }
                }
            }
            floorCapacity.put(VehicleType.BIKE.name(), bikeCapacity);
            floorCapacity.put(VehicleType.CAR.name(), carCapacity);
            floorCapacity.put(VehicleType.TRUCK.name(), TruckCapacity);
            if (vehicleTypes != null){
                floorCapacity = floorCapacity.entrySet().stream().filter(entry -> vehicleTypes.contains(VehicleType.valueOf(entry.getKey()))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            }
            capacity.put(pf, floorCapacity);
        }

        return capacity;
    }
}