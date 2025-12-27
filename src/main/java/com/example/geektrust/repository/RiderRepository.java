package com.example.geektrust.repository;

import com.example.geektrust.entity.Rider;
import java.util.Optional;

public interface RiderRepository {

  void save(Rider rider);
  Optional<Rider> findById(String riderId);

}
