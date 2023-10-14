package com.example.parkingLot2.service;

import com.example.parkingLot2.entity.Slot;
import com.example.parkingLot2.request.SlotRequest;

import java.util.List;

public interface SlotService {
  public Boolean addSlotList (List<SlotRequest> slotRequestList);

//  public Slot updateSlot (List<SlotRequest> slotRequestList);
}
