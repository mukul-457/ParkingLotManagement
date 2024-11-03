package repositories;

import models.Gate;
import models.ParkingLot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ParkingLotRepositoryImpl implements ParkingLotRepository{

    private Map<Long , ParkingLot> parkingLotDb_Id = new HashMap<>();
    private Map<Long, ParkingLot> parkingLotDb_Gate = new HashMap<>();

    public Optional<ParkingLot> getParkingLotByGateId(long gateId){
        if (parkingLotDb_Gate.containsKey(gateId)){
            return Optional.of(parkingLotDb_Gate.get(gateId));
        }
        return Optional.empty();
    }

    public Optional<ParkingLot> getParkingLotById(long id){
        if (parkingLotDb_Id.containsKey(id)){
            return Optional.of(parkingLotDb_Id.get(id));
        }
        return Optional.empty();
    }

    public ParkingLot save(ParkingLot parkingLot){
        parkingLotDb_Id.put(parkingLot.getId(), parkingLot);
        for(Gate gate  : parkingLot.getGates()){
            parkingLotDb_Gate.put(gate.getId(), parkingLot);
        }
        return parkingLot;
    }

}
