package com.example.parkingLot2.controller;

import com.example.parkingLot2.entity.UserCredentials;
import com.example.parkingLot2.service.UserCredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authentication")
public class UserCredentialsController {

  private final UserCredentialsService userCredentialsService;

  @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
  public Boolean registerUsers(@RequestParam String userName, @RequestParam String password,
      @RequestParam String role, @RequestParam String emailId) {
    UserCredentials userCredentials = UserCredentials.builder()
        .emailId(emailId)
        .role(role)
        .userName(userName)
        .build();
   return userCredentialsService.registerUser(userCredentials, password);
  }

  @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public Boolean registerUsers(@RequestParam String userName, @RequestParam String password) {

    return userCredentialsService.checkUserCredentials(userName, password);
  }

}
