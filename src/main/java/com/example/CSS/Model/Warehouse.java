package com.example.CSS.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "Warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long warehouseId;

    @Column(nullable = false, unique = true, length = 100)
    private String warehouseName;

    @Column(length = 255)
    private String warehouseDescription;

    @Column
    private long createdBy;

    @Column
    private LocalDateTime createdAt;

    // Getters and Setters
}