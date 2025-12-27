package com.example.geektrust.appservices;

import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.RiderRepository;
import java.util.Objects;

public class RiderService {

  private final RiderRepository riderRepository;

  public RiderService(RiderRepository riderRepository){
    this.riderRepository = riderRepository;
  }

  public void addRider(Rider rider){
    Objects.requireNonNull(rider, "Rider can't be null if you want to add");

    if(riderRepository.findById(rider.getRiderId()).isPresent()){
      throw new IllegalArgumentException("Rider already present if rider Id: "+ rider.getRiderId());
    }

    riderRepository.save(rider);
  }

  public Rider getRider(String riderId){
    return riderRepository.findById(riderId).orElseThrow(() -> new IllegalArgumentException("Rider not found :"+riderId));
  }

}
