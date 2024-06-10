package com.abhilash.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhilash.MetroEntity.Metrobank;
@Repository
public interface metrobankrepo extends JpaRepository<Metrobank, Integer> {

}
