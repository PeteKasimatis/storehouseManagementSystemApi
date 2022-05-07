package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.ExitReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExitReceiptRepository extends JpaRepository<ExitReceipt, Long> {
}
