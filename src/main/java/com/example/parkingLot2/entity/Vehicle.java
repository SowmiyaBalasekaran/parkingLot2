package com.example.parkingLot2.entity;

import com.example.parkingLot2.entity.enums.VechileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class Vehicle {
  private String vehicleNo;
  private String vehicleOwnerName;
  private LocalDateTime enterTime;
  private int durationInHours;
  private int floorNumber;
  private int slotNumber;
  private VechileModel vechileModel;
  private String ownerName;
}
