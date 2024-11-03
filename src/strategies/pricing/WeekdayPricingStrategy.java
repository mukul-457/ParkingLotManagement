package strategies.pricing;

import models.VehicleType;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WeekdayPricingStrategy implements PricingStrategy{

    @Override
    public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
        long durationInMiliseconds  = exitTime.getTime() - entryTime.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(durationInMiliseconds);
        if (durationInMiliseconds % TimeUnit.HOURS.toMillis(1) != 0){
            hours++;
        }
        return 10.0 * hours;
    }
}
