package strategies.assignment;

import models.ParkingLot;
import models.ParkingSpot;
import models.VehicleType;

import java.util.Optional;

public interface SpotAssignmentStrategy {

    Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType);

}

