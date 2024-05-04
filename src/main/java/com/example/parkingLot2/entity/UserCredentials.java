package com.example.parkingLot2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userCredentials")
@Builder
@AllArgsConstructor
@Data
public class UserCredentials {
  private String userId;
  private String userName;
  private String emailId;
  private byte[] password;
  private String role;
}
