package com.example.parkingLot2.entity;

import com.example.parkingLot2.entity.enums.VechileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {
  private String userName;
  private List<Integer> floorList;
  private int twoWheelersSlotCount;
  private int fourWheelersSlotCount;
  private Map<VechileModel,Double> costPerDuration;
  private Map<Integer, List<Integer>> floorAndTwoWheelerListMap;
  private Map<Integer, List<Integer>> floorAndFloorWheelerListMap;
}
