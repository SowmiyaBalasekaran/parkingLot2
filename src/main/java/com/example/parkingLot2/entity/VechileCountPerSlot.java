package com.example.parkingLot2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VechileCountPerSlot {
  private int totalTwoWheelerCount;
  private int totalFourWheelerCount;
  private boolean isAvailable = true;
}
