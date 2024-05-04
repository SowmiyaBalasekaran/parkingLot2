package com.example.parkingLot2.service;

import com.example.parkingLot2.entity.UserCredentials;

public interface UserCredentialsService {
  public Boolean checkUserCredentials(String userName, String password);
  public Boolean registerUser (UserCredentials userCredentials, String password);
}
