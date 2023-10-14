package com.example.parkingLot2.service.impl;

import com.example.parkingLot2.repository.SlotRepository;
import com.example.parkingLot2.entity.Slot;
import com.example.parkingLot2.entity.VechileCountPerSlot;
import com.example.parkingLot2.request.SlotRequest;
import com.example.parkingLot2.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {

  private final SlotRepository slotRepository;

  @Override
  public Boolean addSlotList(List<SlotRequest> slotRequestList) {
    List<Slot> slotList = slotRepository.findAll();
    if(CollectionUtils.isEmpty(slotList)){
      Map<Integer, Map<String, VechileCountPerSlot>> integerMapMap = new HashMap<>();
      Map<String, VechileCountPerSlot> vechileCountPerSlotMap = new HashMap<>();
      for (SlotRequest slotRequest:slotRequestList) {
        vechileCountPerSlotMap.put(slotRequest.getSlotName(), slotRequest.getVechileCountPerSlot());
        integerMapMap.put(slotRequest.getFloorNumber(), vechileCountPerSlotMap);
      }
      Slot slot = Slot.builder()
          .floorSlotMap(integerMapMap)
          .build();
      slotRepository.insert(slot);
    } else {
      Map<Integer, Map<String, VechileCountPerSlot>> vehicleCountMap = constructSlot(slotRequestList, slotList.get(0));
//      slotList.get(0).getFloorSlotMap().put(vehicleCountMap)
      slotList.get(0).setFloorSlotMap(vehicleCountMap);
      slotRepository.save(slotList.get(0));
    }
    return Boolean.TRUE;
  }

  private Map<Integer, Map<String, VechileCountPerSlot>> constructSlot(List<SlotRequest> slotRequestList,
      Slot slot) {
    Set<Integer> floorListPresent = slot.getFloorSlotMap().keySet();
    for (SlotRequest slotRequest: slotRequestList) {
      if(floorListPresent.contains(slotRequest.getFloorNumber())){
        Map<String, VechileCountPerSlot> vehicleCountPerSlotMap = slot.getFloorSlotMap().get(slotRequest.getFloorNumber());
        vehicleCountPerSlotMap.put(slotRequest.getSlotName(), slotRequest.getVechileCountPerSlot());
        slot.getFloorSlotMap().put(slotRequest.getFloorNumber(), vehicleCountPerSlotMap);
      } else {
        Map<String, VechileCountPerSlot> vehicleCountPerSlotMap = new HashMap<>();
        vehicleCountPerSlotMap.put(slotRequest.getSlotName(), slotRequest.getVechileCountPerSlot());
        slot.getFloorSlotMap().put(slotRequest.getFloorNumber(), vehicleCountPerSlotMap);
      }
    }
    return slot.getFloorSlotMap();
  }

  //  @Override
  //  public Slot updateSlot(List<SlotRequest> slotRequestList) {
  //    Iterator<SlotRequest> slotIterator = slotRequestList.iterator();
  //    while (slotIterator.hasNext()){
  //      SlotRequest slotRequest = slotIterator.next();
  //      if(Objects.nonNull(slotRequest.getSlotName())){
  //
  //      }
  //    }
  //    return null;
  //  }
}
