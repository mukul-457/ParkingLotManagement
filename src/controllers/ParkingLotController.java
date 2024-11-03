package controllers;

import dtos.GetParkingLotCapacityRequestDto;
import dtos.GetParkingLotCapacityResponseDto;
import dtos.Response;
import dtos.ResponseStatus;
import exceptions.InvalidParkingLotException;
import models.ParkingFloor;
import models.VehicleType;
import services.ParkingLotService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public GetParkingLotCapacityResponseDto getParkingLotCapacity(GetParkingLotCapacityRequestDto getParkingLotCapacityRequestDto) {
        GetParkingLotCapacityResponseDto responseDto = new GetParkingLotCapacityResponseDto();

        List<VehicleType> vehicleTypes;

        try{
            if (getParkingLotCapacityRequestDto.getVehicleTypes() != null){
                vehicleTypes = getParkingLotCapacityRequestDto.getVehicleTypes().stream().map(VehicleType::valueOf).toList();
            }else{
                vehicleTypes = Arrays.asList(VehicleType.values())  ;
            }
        }catch(IllegalArgumentException e){
            responseDto.setResponse(new Response(ResponseStatus.FAILURE, "some error occured"));
            return responseDto;
        }

        try{
            Map<ParkingFloor, Map<String, Integer>>  capacityMap = parkingLotService.getParkingLotCapacity(getParkingLotCapacityRequestDto.getParkingLotId(),
                    getParkingLotCapacityRequestDto.getParkingFloorIds(), vehicleTypes);
            responseDto.setCapacityMap(capacityMap);
            Response response = new Response(ResponseStatus.SUCCESS, "Success");
            responseDto.setResponse(response);
        }catch(InvalidParkingLotException e){
            responseDto.setResponse(new Response(ResponseStatus.FAILURE, "some error occured"));
        }
        return responseDto;
    }

}
