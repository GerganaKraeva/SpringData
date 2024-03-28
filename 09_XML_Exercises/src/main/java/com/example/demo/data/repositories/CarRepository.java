package com.example.demo.data.repositories;

import com.example.demo.data.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository <Car, Long> {


    Set<Car> findAllByMakeOrderByTravelledDistanceDesc(String toyota);
}
