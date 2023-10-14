package com.example.parkingLot2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Slot")
public class Slot {
  @Id
  private String _id;
  private Map<Integer, Map<String, VechileCountPerSlot>> floorSlotMap;
}
