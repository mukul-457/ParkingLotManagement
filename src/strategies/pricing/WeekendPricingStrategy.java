package strategies.pricing;


import models.Slab;
import models.VehicleType;
import repositories.SlabRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeekendPricingStrategy implements PricingStrategy{

    private SlabRepository slabRepository;

    public WeekendPricingStrategy(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    @Override
    public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
        List<Slab>  allSlabs = slabRepository.getSortedSlabsByVehicleType(vehicleType);
        long durationInMiliseconds  = exitTime.getTime() - entryTime.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(durationInMiliseconds);
        if (durationInMiliseconds % TimeUnit.HOURS.toMillis(1) != 0){
            hours++;
        }
        double amount = 0;
        for (Slab slab : allSlabs){
            long hourInSalb;
            if (slab.getEndHour() == -1){
                hourInSalb = hours;
            }else{
                hourInSalb = slab.getEndHour() - slab.getStartHour();
            }

            if (hourInSalb < hours){
                amount += slab.getPrice()*hourInSalb;
                hours -= hourInSalb;
            }else{
                amount += slab.getPrice()*hours;
                hours = 0;
            }

            if (hours == 0){
                break ;
            }
        }
        return amount;
    }
}
