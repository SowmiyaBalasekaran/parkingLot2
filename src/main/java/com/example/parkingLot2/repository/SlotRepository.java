package com.example.parkingLot2.repository;

import com.example.parkingLot2.entity.Slot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends MongoRepository<Slot, String> {
}
