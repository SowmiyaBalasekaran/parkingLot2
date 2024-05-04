package com.example.parkingLot2.repository;

import com.example.parkingLot2.entity.UserCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends MongoRepository<UserCredentials, String> {

  public UserCredentials findByUserName (String userName);
}
