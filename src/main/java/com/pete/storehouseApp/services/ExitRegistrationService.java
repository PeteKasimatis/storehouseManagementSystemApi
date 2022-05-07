package com.pete.storehouseApp.services;

import com.pete.storehouseApp.dto.ExitRegistrationDTO;
import com.pete.storehouseApp.models.ExitRegistration;

import java.util.List;

public interface ExitRegistrationService{
    ExitRegistration dtoToEntity(ExitRegistrationDTO exitRegistrationDTO);

    ExitRegistrationDTO entityToDTO(ExitRegistration exitRegistration);

    void deleteByReceiptId(Long id);

    List<ExitRegistrationDTO> getByReceiptId(Long id);
}
