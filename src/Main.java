import models.Vehicle;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class Main {
    private static Map<VehicleType, List<Slab>> slabDb = new HashMap<>();
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        Date d = new Date();
//        Date entry = subtractHoursFromDate(2,30);
//        System.out.println(d.getDay());
        Slab slab  = new Slab();
        slab.setVehicleType(VehicleType.CAR);
        slab.setStartHour(2);
        save(slab);
        Slab slab1 = new Slab();
        slab1.setVehicleType(VehicleType.CAR);
        slab1.setStartHour(0);
        save(slab1);
        List<Slab> allSlabs = slabDb.get(VehicleType.CAR);
        allSlabs.sort((o1,o2) -> Integer.compare(o1.getStartHour(), o2.getStartHour()) );
        System.out.println(allSlabs);

    }
    public static Date subtractHoursFromDate(int hours, int mins) {
        // Getting the current date and time
        Date currentDate = new Date();

        // Creating a Calendar instance and setting it to the current date and time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Subtracting the specified number of hours from the current date and time
        calendar.add(Calendar.HOUR_OF_DAY, -hours);
        calendar.add(Calendar.MINUTE, -mins);

        // Getting the modified date and time
        Date newDate = calendar.getTime();

        return newDate;
    }

    public static Slab save(Slab slab){
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

class Slab{
    private  VehicleType vehicleType;
    private  int startHour;

    public  int getStartHour(){
        return startHour;
    }

    public void setStartHour(int h){
        this.startHour = h;
    }

    public  VehicleType getVehicleType(){
        return vehicleType;
    }
    public void setVehicleType(VehicleType type){
        this.vehicleType = type;
    }
}
enum  VehicleType{
    CAR, BIKE
}