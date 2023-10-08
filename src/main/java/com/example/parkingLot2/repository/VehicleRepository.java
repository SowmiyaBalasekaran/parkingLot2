package com.example.parkingLot2.repository;

import com.example.parkingLot2.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
  public Vehicle findByVehicleNo(String vehicleNo);

}
