package com.pete.storehouseApp.services;

import com.pete.storehouseApp.dto.EntryRegistrationDTO;
import com.pete.storehouseApp.models.EntryRegistration;

import java.util.List;

public interface EntryRegistrationService {

    EntryRegistration dtoToEntity(EntryRegistrationDTO entryRegistrationDTO);

    void deleteByReceiptId(Long id);

    List<EntryRegistrationDTO> getByReceiptId(Long id);

    EntryRegistrationDTO entityToDTO(EntryRegistration entryRegistration);

}
