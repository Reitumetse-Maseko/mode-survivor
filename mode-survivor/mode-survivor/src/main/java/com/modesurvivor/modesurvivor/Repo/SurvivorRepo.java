package com.modesurvivor.modesurvivor.Repo;

import com.modesurvivor.modesurvivor.Entity.SurvivorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurvivorRepo extends JpaRepository<SurvivorEntity, Long> {
    List<SurvivorEntity> findByIsInfected(boolean isInfected);
}

