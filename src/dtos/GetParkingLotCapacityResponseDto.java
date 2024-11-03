package dtos;

import models.ParkingFloor;

import java.util.Map;

public class GetParkingLotCapacityResponseDto {

    private Response response;

    private Map<ParkingFloor, Map<String, Integer>> capacityMap;

    public Map<ParkingFloor, Map<String, Integer>> getCapacityMap() {
        return capacityMap;
    }

    public void setCapacityMap(Map<ParkingFloor, Map<String, Integer>> capacityMap) {
        this.capacityMap = capacityMap;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}

