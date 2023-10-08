package com.example.parkingLot2.service.impl;

import com.example.parkingLot2.entity.User;
import com.example.parkingLot2.entity.Vehicle;
import com.example.parkingLot2.entity.enums.VechileModel;
import com.example.parkingLot2.modal.GetParkingLotResponse;
import com.example.parkingLot2.modal.VehicleLeavingResponse;
import com.example.parkingLot2.repository.UserRepository;
import com.example.parkingLot2.repository.VehicleRepository;
import com.example.parkingLot2.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingLotServiceImpl implements ParkingLotService {

  @Value("${floor.limit}")
  private int floorLimit;
  private final UserRepository userRepository;
  private final VehicleRepository vehicleRepository;

  @Override
  public GetParkingLotResponse getParkingLot(String userName,
      VechileModel vechileModel,
      String vehicleNumber,
      String vehicleOwnerName) {
    Optional<User> user = userRepository.findByUserName(userName);

    Map<Integer, List<Integer>> floorSlotMap = new HashMap<>();
    if (VechileModel.TWO_WHEELER.equals(vechileModel)) {
      floorSlotMap = user.get().getFloorAndTwoWheelerListMap();
    } else if (VechileModel.FOUR_WHEELER.equals(vechileModel)) {
      floorSlotMap = user.get().getFloorAndTwoWheelerListMap();
    } else {
      throw new InputMismatchException();
    }

    for (int i = 0; i <= floorLimit; i++) {
      List<Integer> slotList = floorSlotMap.get(i);
      if (CollectionUtils.isEmpty(slotList)) {
        for (int j = 1; j <= slotList.size(); j++) {
          if (slotList.get(j) == 0) {
            vehicleRepository.save(Vehicle.builder()
                .vehicleNo(vehicleNumber)
                .ownerName(userName)
                .vechileModel(vechileModel)
                .vehicleOwnerName(vehicleOwnerName)
                .enterTime(LocalDateTime.now())
                .build());
            return GetParkingLotResponse.builder()
                .floor(i)
                .slotNo(j)
                .build();
          }
        }
      }
    }
    return null;
  }

  @Override
  public VehicleLeavingResponse registerLeavingVehicle(String vehicleNumber) {
    LocalDateTime localDateTime = LocalDateTime.now();
    Vehicle vehicle = vehicleRepository.findByVehicleNo(vehicleNumber);
    int duration = localDateTime.getHour() - vehicle.getEnterTime().getHour();
    Optional<User> user = userRepository.findByUserName(vehicle.getOwnerName());
    Double cost;
    List<Integer> slotList = new ArrayList<>();
    if (vehicle.getVechileModel().equals(VechileModel.TWO_WHEELER)) {
      cost = user.get().getCostPerDuration().get(VechileModel.TWO_WHEELER) * duration;
      slotList = user.get().getFloorAndTwoWheelerListMap().get(vehicle.getFloorNumber());
      slotList.set(vehicle.getSlotNumber(), 0);
      return VehicleLeavingResponse.builder()
          .vehicleNumber(vehicleNumber)
          .duration(duration)
          .totalCost(cost)
          .build();

    } else if (vehicle.getVechileModel().equals(VechileModel.FOUR_WHEELER)) {
      cost = user.get().getCostPerDuration().get(VechileModel.FOUR_WHEELER) * duration;
      slotList = user.get().getFloorAndTwoWheelerListMap().get(vehicle.getFloorNumber());
      slotList.set(vehicle.getSlotNumber(), 0);
      return VehicleLeavingResponse.builder()
          .vehicleNumber(vehicleNumber)
          .duration(duration)
          .totalCost(cost)
          .build();
    } else {
      throw new InputMismatchException();
    }
  }
}
