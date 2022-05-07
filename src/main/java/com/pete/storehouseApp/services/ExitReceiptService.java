package com.pete.storehouseApp.services;

import com.pete.storehouseApp.dto.ExitReceiptDTO;

import java.util.List;

public interface ExitReceiptService {

    ExitReceiptDTO create(ExitReceiptDTO exitReceiptDTO);

    ExitReceiptDTO update(ExitReceiptDTO exitReceiptDTO);

    void delete(Long id);

    List<ExitReceiptDTO> findAll();

    ExitReceiptDTO findById(Long id);
}
