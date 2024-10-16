package com.example.CSS.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    private String itemName;

    private String itemDescription;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
}