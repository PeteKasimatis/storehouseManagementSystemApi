package com.pete.storehouseApp.validators;

import com.pete.storehouseApp.dto.EntryRegistrationDTO;
import com.pete.storehouseApp.errorHandling.errorReport.ErrorReport;
import com.pete.storehouseApp.repositories.ProductRepository;
import com.pete.storehouseApp.repositories.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryRegistrationValidator {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShelfRepository shelfRepository;

    @Autowired
    ErrorReport errorReport;

    public void validateCreate(EntryRegistrationDTO entryRegistrationDTO){
        errorReport.clear();

        if (entryRegistrationDTO.getQuantity() == null){
            errorReport.addError("Quantity cannot be empty. ");
        }

        if (entryRegistrationDTO.getQuantity() <= 0){
            errorReport.addError("Quantity must be positive. ");
        }

        if (entryRegistrationDTO.getProductBarcode() == null || entryRegistrationDTO.getProductBarcode().isEmpty()){
            errorReport.addError("Barcode cannot be empty. ");
        }

        if (productRepository.findByBarcode(entryRegistrationDTO.getProductBarcode()) == null){
            errorReport.addError("Product with barcode: " + entryRegistrationDTO.getProductBarcode() + " does not exist. ");
        }

        if (entryRegistrationDTO.getShelfIdentifier() == null || entryRegistrationDTO.getShelfIdentifier().isEmpty()){
            errorReport.addError("Shelf identifier cannot be empty. ");
        }

        if (shelfRepository.findByIdentifier(entryRegistrationDTO.getShelfIdentifier()) == null){
            errorReport.addError("Shelf with identifier: " + entryRegistrationDTO.getShelfIdentifier() + " does not exist. ");
        }

        if (entryRegistrationDTO.getId() != null){
            entryRegistrationDTO.setId(0L);
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

}
