package com.example.parkingLot2.service;

import com.example.parkingLot2.entity.User;
import com.example.parkingLot2.entity.enums.VechileModel;

import java.util.Map;

public interface UserService {
  public User addUser(String userName, int totalSlot, Map<VechileModel,Double> costPerDuration);
}
