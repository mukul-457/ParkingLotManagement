package strategies.pricing;

import repositories.SlabRepository;

import java.util.Date;

public class PricingStrategyFactory {

    private SlabRepository slabRepository;

    public PricingStrategyFactory(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    public PricingStrategy getPricingStrategy(Date exitTime){
        if (exitTime.getDay() == 0 || exitTime.getDay() == 6 ){
            return new WeekendPricingStrategy(slabRepository);
        }
        return new WeekdayPricingStrategy();
    }
}