package com.arun.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arun.MetroEntity.Metroentity;
@Repository
public interface metrorepo extends JpaRepository<Metroentity, Integer> {

}
