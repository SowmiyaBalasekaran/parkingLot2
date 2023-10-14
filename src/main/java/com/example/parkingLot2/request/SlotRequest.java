package com.example.parkingLot2.request;

import com.example.parkingLot2.entity.VechileCountPerSlot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SlotRequest {
  private int floorNumber;
  private String slotName;
  private VechileCountPerSlot vechileCountPerSlot;
}
