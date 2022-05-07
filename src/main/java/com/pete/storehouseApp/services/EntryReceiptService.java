package com.pete.storehouseApp.services;

import com.pete.storehouseApp.dto.EntryReceiptDTO;

import java.util.List;

public interface EntryReceiptService{

    EntryReceiptDTO create(EntryReceiptDTO entryReceiptDTO);

    EntryReceiptDTO update(EntryReceiptDTO entryReceiptDTO);

    void delete(Long id);

    List<EntryReceiptDTO> findAll();

    EntryReceiptDTO findById(Long id);
}
