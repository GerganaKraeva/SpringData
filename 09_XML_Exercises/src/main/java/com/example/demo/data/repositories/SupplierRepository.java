package com.example.demo.data.repositories;

import com.example.demo.data.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

   Set<Supplier> findAllByIsImporter(boolean isImporter);

}
