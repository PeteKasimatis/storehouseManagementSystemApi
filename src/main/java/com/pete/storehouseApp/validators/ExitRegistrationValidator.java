package com.pete.storehouseApp.validators;

import com.pete.storehouseApp.dto.ExitRegistrationDTO;
import com.pete.storehouseApp.errorHandling.errorReport.ErrorReport;
import com.pete.storehouseApp.models.ProductShelfId;
import com.pete.storehouseApp.repositories.ProductRepository;
import com.pete.storehouseApp.repositories.ProductShelfRepository;
import com.pete.storehouseApp.repositories.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExitRegistrationValidator {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShelfRepository shelfRepository;

    @Autowired
    ErrorReport errorReport;

    @Autowired
    ProductShelfRepository productShelfRepository;

    public void validate(ExitRegistrationDTO exitRegistrationDTO){
        errorReport.clear();

        if (exitRegistrationDTO.getQuantity() == null){
            errorReport.addError("Quantity cannot be empty. ");
        }

        if (exitRegistrationDTO.getQuantity() <= 0){
            errorReport.addError("Quantity must be positive. ");
        }

        if (exitRegistrationDTO.getProductBarcode() == null || exitRegistrationDTO.getProductBarcode().isEmpty()){
            errorReport.addError("Barcode cannot be empty. ");
        }

        if (productRepository.findByBarcode(exitRegistrationDTO.getProductBarcode()) == null){
            errorReport.addError("Product with barcode: " + exitRegistrationDTO.getProductBarcode() + " does not exist. ");
        }

        if (exitRegistrationDTO.getShelfIdentifier() == null || exitRegistrationDTO.getShelfIdentifier().isEmpty()){
            errorReport.addError("Shelf identifier cannot be empty. ");
        }

        if (shelfRepository.findByIdentifier(exitRegistrationDTO.getShelfIdentifier()) == null){
            errorReport.addError("Shelf with identifier: " + exitRegistrationDTO.getShelfIdentifier() + " does not exist. ");
        }

        //if quantity to be exited is greater than quantity in given shelf for the given product throw error
        if (productShelfRepository.getById(new ProductShelfId(productRepository.findByBarcode(exitRegistrationDTO.getProductBarcode()).getId()
                , shelfRepository.findByIdentifier(exitRegistrationDTO.getShelfIdentifier()).getId())).getQuantity() - exitRegistrationDTO.getQuantity() < 0){

            errorReport.addError("Error: The quantity that you are trying to extract is greater than the quantity on the shelf. ");
        }


        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }
}
