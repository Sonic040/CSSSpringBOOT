package com.example.CSS.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

    @Setter
    @Getter
    @Entity
    @Table(name = "SupplyDocuments")
    public class SupplyDocuments {

   /*    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "supply_document_id")
        private Long supplydocumentid;

        @Column(name = "document_name", nullable = false, length = 100)
        private String documentname;

        @Column(name = "document_subject",length = 255)
        private String documentsubject;

        @Column(name = "warehouse_id")
        private int warehouseid;
        @Column(name = "item_id")
        private int itemid;

        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @Column(name = "status")
        private String status;
        @Column
        private int createdBy;

*/
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "supply_document_id")
        private long supplyDocumentId;

        @Column(name = "document_name", nullable = false, length = 100)
        private String documentName;

        @Column(name = "document_subject", length = 255)
        private String documentSubject;

        @Column(name = "warehouse_id")
        private int warehouseId;

        @Column(name = "item_id")
        private int itemId;

        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @Column(name = "status", length = 20)
        private String status;

        @Column(name = "created_by")
        private long createdBy;

        // Getters and Setters
    }

