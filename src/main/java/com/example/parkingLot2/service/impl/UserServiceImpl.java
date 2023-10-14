package com.example.parkingLot2.service.impl;

import com.example.parkingLot2.entity.Slot;
import com.example.parkingLot2.entity.User;
import com.example.parkingLot2.entity.VechileCountPerSlot;
import com.example.parkingLot2.entity.enums.VechileModel;
import com.example.parkingLot2.repository.SlotRepository;
import com.example.parkingLot2.repository.UserRepository;
import com.example.parkingLot2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


  @Value("${floor.limit}")
  private int floorLimit;
  private final SlotRepository slotRepository;
  private final UserRepository userRepository;
  @Override
  public User addUser(String userName, int totalSlot, Map<VechileModel,Double> costPerDuration) {
    User user = User.builder()
        .userName(userName)
        .costPerDuration(costPerDuration)
        .build();
    List<Integer> floorList = new ArrayList<>();
    Map<Integer, Map<String, List<Integer>>> floorAndTwoWheelerListMap = new HashMap<>();
    Map<Integer, Map<String, List<Integer>>> floorAndFloorWheelerListMap = new HashMap<>();
    Map<String, List<Integer>> slotVehicleMap = new HashMap<>();
    AtomicInteger twoWheelerCount = new AtomicInteger(0);
    AtomicInteger flourWheelerCount = new AtomicInteger(0);

   List<Slot> slotList =  slotRepository.findAll();
    Map<Integer, Map<String, VechileCountPerSlot>> integerMapMap = slotList.get(0).getFloorSlotMap();
    final int[] totalSlotFinal = {totalSlot};
    integerMapMap.forEach((floorNumber, slotDetails)->{
      slotDetails.forEach((slotNumber, vehicleCountDetails) ->{
        if(vehicleCountDetails.isAvailable() && totalSlotFinal[0] !=0){
          floorList.add(floorNumber);
          twoWheelerCount.addAndGet(vehicleCountDetails.getTotalTwoWheelerCount());
          flourWheelerCount.addAndGet(vehicleCountDetails.getTotalFourWheelerCount());
          List<Integer> list = new ArrayList<>(Collections.nCopies(vehicleCountDetails.getTotalTwoWheelerCount(), 0));
          slotVehicleMap.put(slotNumber, list);
          floorAndTwoWheelerListMap.put(floorNumber, slotVehicleMap);
          floorAndFloorWheelerListMap.put(floorNumber, slotVehicleMap);
          totalSlotFinal[0]--;
        }
      });
    });
    user.setFloorList(floorList);
    user.setFloorSlotFlourWheelerListMap(floorAndFloorWheelerListMap);
    user.setFloorSlotTwoWheelerListMap(floorAndTwoWheelerListMap);
    user.setTwoWheelersSlotCount(twoWheelerCount.get());
    user.setFourWheelersSlotCount(flourWheelerCount.get());
    userRepository.insert(user);
    return user;
  }
}
