package com.example.parkingLot2.request;

import com.example.parkingLot2.entity.enums.VechileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
  private String userName;
  private int totalSlot;
  private Map<VechileModel, Double> costPerDuration;
}
