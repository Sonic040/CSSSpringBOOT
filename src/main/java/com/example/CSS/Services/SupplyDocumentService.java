package com.example.CSS.Services;


import com.example.CSS.Model.SupplyDocuments;
import com.example.CSS.Repository.SupplyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplyDocumentService {

    @Autowired
    private SupplyDocumentRepository supplydocumentRepository;

    // Wrap the procedure call in a transaction
    @Transactional(readOnly = false)
    public List<SupplyDocuments> getWarehousesByManager(long userid, int usertype) {
        return supplydocumentRepository.findByUser(userid,usertype);
    }
}
