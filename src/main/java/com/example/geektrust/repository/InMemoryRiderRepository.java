package com.example.geektrust.repository;

import com.example.geektrust.entity.Rider;
import java.util.HashMap;
import java.util.Optional;

public class InMemoryRiderRepository implements RiderRepository{

  private final HashMap < String, Rider > riders = new HashMap<>();

  @Override
  public void save(Rider rider){
    riders.put(rider.getRiderId(), rider);
  }

  @Override
  public Optional<Rider> findById(String riderId){
    return Optional.ofNullable(riders.get(riderId));
  }



}
