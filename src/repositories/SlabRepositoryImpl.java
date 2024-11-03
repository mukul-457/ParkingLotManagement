package repositories;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import  models.*;
public class SlabRepositoryImpl implements SlabRepository{
    private Map<VehicleType, List<Slab>> slabDb = new HashMap<>();
    public List<Slab> getSortedSlabsByVehicleType(VehicleType vehicleType){
        List<Slab> allSlabs = slabDb.get(vehicleType);
        allSlabs.sort((o1,o2) -> Integer.compare(o1.getStartHour(), o2.getStartHour()) );
        return allSlabs;
    }

    public  Slab save(Slab slab){
        List<Slab> slabs;
        if (slabDb.containsKey(slab.getVehicleType())){
            slabs = slabDb.get(slab.getVehicleType());
        }else{
            slabs = new ArrayList<>();
        }
        slabs.add(slab);
        slabDb.put(slab.getVehicleType(), slabs);
        return slab;
    }
}

