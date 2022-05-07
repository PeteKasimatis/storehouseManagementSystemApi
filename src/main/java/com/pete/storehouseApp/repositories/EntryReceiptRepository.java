package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.EntryReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryReceiptRepository extends JpaRepository<EntryReceipt, Long> {

}
