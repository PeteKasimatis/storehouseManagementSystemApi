package com.pete.storehouseApp.services;

import com.pete.storehouseApp.dto.ProductShelfDTO;
import com.pete.storehouseApp.models.EntryRegistration;
import com.pete.storehouseApp.models.ExitRegistration;

import java.util.List;

public interface ProductShelfService {


    List<ProductShelfDTO> findAll();

    List<ProductShelfDTO> findByBarcode(String barcode);

    List<ProductShelfDTO> findByIdentifier(String identifier);

    //action string defines whether we add or subtract from the quantity
    void updateProductInShelf(EntryRegistration entryRegistration , String action);

    void updateProductInShelf(ExitRegistration exitRegistration, String action);

    }
