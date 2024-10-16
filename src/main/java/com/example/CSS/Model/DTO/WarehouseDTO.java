package com.example.CSS.Model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class WarehouseDTO {
    private String warehouseName;
    private String warehouseDescription;
    private List<ItemDTO> items;
}