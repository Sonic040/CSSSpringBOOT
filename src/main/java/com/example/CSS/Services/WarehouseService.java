package com.example.CSS.Services;

import com.example.CSS.Model.Warehouse;
import com.example.CSS.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    // Wrap the procedure call in a transaction
    @Transactional(readOnly = false)
    public List<Warehouse> getWarehousesByManager(long managerId) {
        return warehouseRepository.findByCreatedBy(managerId);
    }
}
