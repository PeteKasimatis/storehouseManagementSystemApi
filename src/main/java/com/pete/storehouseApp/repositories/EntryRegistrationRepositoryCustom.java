package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.EntryRegistration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EntryRegistrationRepositoryCustom {

    void deleteByReceiptId(Long id);

    List<EntryRegistration> getByReceiptId(Long id);

}
