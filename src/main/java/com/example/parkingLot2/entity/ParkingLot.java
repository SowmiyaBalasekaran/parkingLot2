package com.example.parkingLot2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {
  private String ownerName;
  private int totalTwoWheelerCountPerDay;
  private int totalFourWheelerCountPerDay;
}
