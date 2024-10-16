package com.example.CSS.Repository;


import com.example.CSS.Model.SupplyDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface SupplyDocumentRepository extends JpaRepository<SupplyDocuments, Long> {


    @Procedure("GetSupplyDocumentsByUser")
    List<SupplyDocuments> findByUser(long userid, int usertype);

}