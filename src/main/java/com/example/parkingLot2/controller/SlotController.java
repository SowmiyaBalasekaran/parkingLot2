package com.example.parkingLot2.controller;

import com.example.parkingLot2.entity.Slot;
import com.example.parkingLot2.entity.User;
import com.example.parkingLot2.request.SlotRequest;
import com.example.parkingLot2.request.UserRequest;
import com.example.parkingLot2.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SlotController {
  private final SlotService slotService;

  @PostMapping(value = "/_addSlot", produces = MediaType.APPLICATION_JSON_VALUE)
  public Boolean addSlot(@RequestBody List<SlotRequest> slotRequests){
    return slotService.addSlotList(slotRequests);
  }
}
