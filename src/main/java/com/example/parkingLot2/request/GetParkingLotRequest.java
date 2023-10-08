package com.example.parkingLot2.request;

import com.example.parkingLot2.entity.enums.VechileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetParkingLotRequest {
  private String userName;
  private VechileModel vehicleModel;
  private String vehicleNumber;
  private String vehicleOwnerName;
}
