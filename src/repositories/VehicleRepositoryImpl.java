package repositories;

import models.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class VehicleRepositoryImpl implements VehicleRepository{
    private Map<String , Vehicle> vehicleDb = new HashMap<>();

    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber){
        if (vehicleDb.containsKey(registrationNumber)){
            return Optional.of(vehicleDb.get(registrationNumber));
        }
        return Optional.empty();
    }

    public Vehicle save(Vehicle vehicle){
        vehicleDb.put(vehicle.getRegistrationNumber(), vehicle);
        return vehicle;
    }
}
