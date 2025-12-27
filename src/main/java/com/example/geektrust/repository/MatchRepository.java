package com.example.geektrust.repository;

import com.example.geektrust.entity.MatchResult;
import java.util.Optional;

public interface MatchRepository {

  void save(MatchResult matchResult);
  Optional<MatchResult> findByRiderId(String riderId);
  void clear(String riderId);

}
