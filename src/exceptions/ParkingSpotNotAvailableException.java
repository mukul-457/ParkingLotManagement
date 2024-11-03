package exceptions;


public class ParkingSpotNotAvailableException extends Exception{
    public ParkingSpotNotAvailableException(String message) {
        super(message);
    }
}