package strategies.pricing;

import models.VehicleType;

import java.util.Date;

public interface PricingStrategy {
    double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType);
}