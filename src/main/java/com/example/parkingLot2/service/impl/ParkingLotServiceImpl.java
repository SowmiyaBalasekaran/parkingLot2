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
import java.util.Objects;
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
//    Vehicle vehicle = vehicleRepository.findByVehicleNo(vehicleNumber);
//        if(!Objects.isNull(vehicle) && !vehicle.isLeft()){
//          return new GetParkingLotResponse();
//        }

    GetParkingLotResponse getParkingLotResponse = new GetParkingLotResponse();
    List<GetParkingLotResponse> getParkingLotResponseList = new ArrayList<>();

    Map<Integer, Map<String, List<Integer>>> floorSlotVehicleListMap = new HashMap<>();
    if (VechileModel.TWO_WHEELER.equals(vechileModel)) {
      floorSlotVehicleListMap = user.get().getFloorSlotTwoWheelerListMap();
    } else if (VechileModel.FOUR_WHEELER.equals(vechileModel)) {
      floorSlotVehicleListMap = user.get().getFloorSlotFlourWheelerListMap();
    } else {
      throw new InputMismatchException();
    }

    for (int i =0; i<=floorLimit;i++){
      Map<String, List<Integer>> slotVehicleListMap = floorSlotVehicleListMap.get(i);
      int finalI = i;
      if(Objects.nonNull(slotVehicleListMap)){
        slotVehicleListMap.forEach((slotName, vehicleAvailableList) -> {
          for (int j =0 ;j < vehicleAvailableList.size(); j++){
            if(vehicleAvailableList.get(j) == 0){
              vehicleRepository.save(Vehicle.builder()
                  .vehicleNo(vehicleNumber)
                  .ownerName(userName)
                  .vechileModel(vechileModel)
                  .floorNumber(finalI)
                  .slotNumber(slotName)
                  .vehiclePositionNumber(j)
                  .vehicleOwnerName(vehicleOwnerName)
                  .enterTime(LocalDateTime.now())
                  .build());
              vehicleAvailableList.set(j,1);
              getParkingLotResponse.setFloor(finalI);
              getParkingLotResponse.setSlotNo(slotName);
              getParkingLotResponseList.add(getParkingLotResponse);
              return;

            }
          }
        });
      }

    }
    if(CollectionUtils.isEmpty(getParkingLotResponseList)){
      return new GetParkingLotResponse();
    }else {
      return getParkingLotResponseList.get(0);
    }
  }

  @Override
  public VehicleLeavingResponse registerLeavingVehicle(String vehicleNumber) {
    LocalDateTime localDateTime = LocalDateTime.now();
    Vehicle vehicle = vehicleRepository.findByVehicleNo(vehicleNumber);
    int duration = localDateTime.getHour() - vehicle.getEnterTime().getHour();
    Optional<User> user = userRepository.findByUserName(vehicle.getOwnerName());
    Double cost;
    Map<String, List<Integer>> slotVehicleMap = new HashMap<>();
    if (vehicle.getVechileModel().equals(VechileModel.TWO_WHEELER)) {
      cost = user.get().getCostPerDuration().get(VechileModel.TWO_WHEELER) * duration;
      slotVehicleMap = user.get().getFloorSlotTwoWheelerListMap().get(vehicle.getFloorNumber());
      slotVehicleMap.get(vehicle.getSlotNumber()).set(vehicle.getVehiclePositionNumber(), 0);
      vehicle.setLeft(true);
      vehicleRepository.save(vehicle);
      return VehicleLeavingResponse.builder()
          .vehicleNumber(vehicleNumber)
          .duration(duration)
          .totalCost(cost)
          .build();

    } else if (vehicle.getVechileModel().equals(VechileModel.FOUR_WHEELER)) {
      cost = user.get().getCostPerDuration().get(VechileModel.FOUR_WHEELER) * duration;
      slotVehicleMap = user.get().getFloorSlotTwoWheelerListMap().get(vehicle.getFloorNumber());
      slotVehicleMap.get(vehicle.getSlotNumber()).set(vehicle.getVehiclePositionNumber(), 0);
      vehicle.setLeft(true);
      vehicleRepository.save(vehicle);
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
