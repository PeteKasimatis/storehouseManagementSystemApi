package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.ExitRegistration;

import java.util.List;

public interface ExitRegistrationRepositoryCustom {

    void deleteByReceiptId(Long id);

    List<ExitRegistration> getByReceiptId(Long id);
}
