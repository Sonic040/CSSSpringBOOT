package com.example.CSS.Controllers;


import com.example.CSS.Model.DTO.SupplyDocumentDTO;
import com.example.CSS.Model.SupplyDocuments;
import com.example.CSS.Model.User;
import com.example.CSS.Repository.UserRepository;
import com.example.CSS.Repository.SupplyDocumentRepository;
import com.example.CSS.Services.SupplyDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = {"Content-Type", "Authorization"})
@RestController
@RequestMapping("/api/supplydocument")
public class SupplyDocumentController {


    @Autowired
    private SupplyDocumentRepository supplydocumentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupplyDocumentService supplydocumentService;


    @GetMapping("/getAll")
    public ResponseEntity<List<SupplyDocuments>> getAllSupplyDocument(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        List<SupplyDocuments> supplydocument = supplydocumentService.getWarehousesByManager( user.getUserId(),user.getUserType());
        return ResponseEntity.ok(supplydocument);
    }

    @PostMapping
    public ResponseEntity<SupplyDocuments> addSupplyDocument(@RequestBody  SupplyDocumentDTO supplydocumentDTO, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        SupplyDocuments supplydocument = new SupplyDocuments();
        supplydocument.setDocumentName(supplydocumentDTO.getDocumentname());
        supplydocument.setDocumentSubject(supplydocumentDTO.getDocumentsubject());
        supplydocument.setWarehouseId(supplydocumentDTO.getWarehouseid());
        supplydocument.setItemId(supplydocumentDTO.getItemid());
        supplydocument.setCreatedBy(user.getUserType());
        supplydocument.setCreatedBy(user.getUserId());
        supplydocument.setCreatedAt(LocalDateTime.now());

        SupplyDocuments savedSupplyDocument = supplydocumentRepository.save(supplydocument);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplyDocument);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplyDocument(@PathVariable Long id) {
        supplydocumentRepository.deleteById(id);
        return ResponseEntity.ok("تم حذف الوثيقة بنجاح");
    }

}
