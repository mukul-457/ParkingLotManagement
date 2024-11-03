package strategies.assignment;
import models.*;
import java.util.Optional;
public class SimpleSpotAssignment implements SpotAssignmentStrategy{

    public Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType){

        for(ParkingFloor floor : parkingLot.getParkingFloors()){
            if (floor.getStatus() == FloorStatus.OPERATIONAL){
                for (ParkingSpot spot : floor.getSpots()){
                    if (spot.getStatus() == ParkingSpotStatus.AVAILABLE && spot.getSupportedVehicleType() == vehicleType){
                        return Optional.of(spot);
                    }
                }
            }
        }
        return Optional.empty();
    }
}

