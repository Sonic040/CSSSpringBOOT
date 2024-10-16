package com.example.CSS.Controllers;

import com.example.CSS.Model.DTO.WarehouseDTO;
import com.example.CSS.Model.Item;
import com.example.CSS.Model.User;
import com.example.CSS.Model.Warehouse;
import com.example.CSS.Repository.UserRepository;
import com.example.CSS.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.CSS.Services.WarehouseService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "If-Match")
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Warehouse>> getAllWarehouses(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        List<Warehouse> warehouses = warehouseService.getWarehousesByManager(user.getUserId());
        return ResponseEntity.ok(warehouses);
    }

    @PostMapping
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody WarehouseDTO warehouseDTO, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName(warehouseDTO.getWarehouseName());
        warehouse.setWarehouseDescription(warehouseDTO.getWarehouseDescription());
        warehouse.setCreatedBy(user.getUserId()); // Set user ID correctly
        warehouse.setCreatedAt(LocalDateTime.now());

        // Create and set items if provided
        if (warehouseDTO.getItems() != null) {
            List<Item> items = warehouseDTO.getItems().stream().map(itemDTO -> {
                Item item = new Item();
                item.setItemName(itemDTO.getItemName());
                item.setItemDescription(itemDTO.getItemDescription());
                item.setQuantity(itemDTO.getQuantity());
                item.setWarehouse(warehouse);
                return item;
            }).collect(Collectors.toList());

            // Here you should save the items if you have a repository or service for it
            // For example, if you have an itemRepository:
            // itemRepository.saveAll(items);
        }

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWarehouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable Long id) {
        warehouseRepository.deleteById(id);
        return ResponseEntity.ok("تم حذف المخزن بنجاح");
    }
}