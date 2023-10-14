package com.example.parkingLot2.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetParkingLotResponse {
  private int floor;
  private String slotNo;
}
