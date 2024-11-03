package repositories;


import models.Invoice;
import models.Slab;
import models.VehicleType;


import java.util.List;

public interface SlabRepository {

    public List<Slab> getSortedSlabsByVehicleType(VehicleType vehicleType);

    public Slab save(Slab slab);
}
