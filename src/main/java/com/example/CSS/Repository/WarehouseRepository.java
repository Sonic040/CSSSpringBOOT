package com.example.CSS.Repository;

import com.example.CSS.Model.User;
import com.example.CSS.Model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {


    @Procedure("GetWarehousesByManager")
    List<Warehouse> findByCreatedBy(long createdby);
    Optional<Warehouse> findByWarehouseName(String warehouseName);
}