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
public class ProductShelfValidator {

    @Autowired
    ProductShelfRepository productShelfRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShelfRepository shelfRepository;

    @Autowired
    ErrorReport errorReport;

    public void validate(ExitRegistrationDTO exitRegistrationDTO){
        errorReport.clear();

        ProductShelfId productShelfId = new ProductShelfId(productRepository.findByBarcode(exitRegistrationDTO.getProductBarcode()).getId()
                , shelfRepository.findByIdentifier(exitRegistrationDTO.getShelfIdentifier()).getId());

        //check if association between shelf and product exists
        if (!productShelfRepository.existsById(productShelfId)){

            errorReport.addError("Shelf with identifier: " + exitRegistrationDTO.getShelfIdentifier()
                    + " does not contain product with barcode: " + exitRegistrationDTO.getProductBarcode()
                    + ". ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateBarcode(String barcode){
        errorReport.clear();

        if(productRepository.findByBarcode(barcode) == null){

            errorReport.addError("Product with given barcode does not exist. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateIdentifier(String identifier){
        errorReport.clear();

        if(shelfRepository.findByIdentifier(identifier) == null){

            errorReport.addError("Shelf with given identifier does not exist. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }
}
