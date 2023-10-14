package com.example.parkingLot2.service;


import com.example.parkingLot2.entity.enums.VechileModel;
import com.example.parkingLot2.modal.GetParkingLotResponse;
import com.example.parkingLot2.modal.VehicleLeavingResponse;
import org.springframework.stereotype.Service;

@Service
public interface ParkingLotService {
  public GetParkingLotResponse getParkingLot(String userName, VechileModel vechileModel,
      String vehicleNumber, String vehicleOwnerName);

  public VehicleLeavingResponse registerLeavingVehicle(String vehicleNumber);
}
