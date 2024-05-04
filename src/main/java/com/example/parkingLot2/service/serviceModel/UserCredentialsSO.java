package com.example.parkingLot2.service.serviceModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialsSO {
  private String userName;
  private String role;
}
