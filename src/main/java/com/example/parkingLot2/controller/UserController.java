package com.example.parkingLot2.controller;

import com.example.parkingLot2.entity.User;
import com.example.parkingLot2.modal.GetParkingLotResponse;
import com.example.parkingLot2.request.GetParkingLotRequest;
import com.example.parkingLot2.request.UserRequest;
import com.example.parkingLot2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping(value = "/_addUser", produces = MediaType.APPLICATION_JSON_VALUE)
  public User addUser(@RequestBody UserRequest userRequest){
    return userService.addUser(userRequest.getUserName(), userRequest.getTotalSlot(),
        userRequest.getCostPerDuration());
  }
}
