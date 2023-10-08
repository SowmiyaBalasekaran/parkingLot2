package com.example.parkingLot2.controller;

import com.example.parkingLot2.modal.GetParkingLotResponse;
import com.example.parkingLot2.modal.VehicleLeavingResponse;
import com.example.parkingLot2.request.GetParkingLotRequest;
import com.example.parkingLot2.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParkingLotController {

  private ParkingLotService parkingLotService;

  @PostMapping(value = "/_getParkingSlot", produces = MediaType.APPLICATION_JSON_VALUE)
  public GetParkingLotResponse getParkingLot(@RequestBody GetParkingLotRequest getParkingLotRequest){
    return parkingLotService.getParkingLot(getParkingLotRequest.getUserName(), getParkingLotRequest.getVehicleModel(),
        getParkingLotRequest.getVehicleNumber(), getParkingLotRequest.getVehicleOwnerName());
  }

  @GetMapping(value = "/vehicleLeaving", produces = MediaType.APPLICATION_JSON_VALUE)
  public VehicleLeavingResponse vehicleLeaving(@RequestParam String vehicleNumber){
    return parkingLotService.registerLeavingVehicle(vehicleNumber);
  }
}
