package com.pete.storehouseApp.validators;

import com.pete.storehouseApp.dto.ProductDTO;
import com.pete.storehouseApp.errorHandling.errorReport.ErrorReport;
import com.pete.storehouseApp.models.Product;
import com.pete.storehouseApp.repositories.ProductRepository;
import com.pete.storehouseApp.services.ProductShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductValidator {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ErrorReport errorReport;

    @Autowired
    ProductShelfService productShelfService;

    public void validateCreate(ProductDTO productDTO){

        errorReport.clear();

        if (productDTO.getDescription() == null || productDTO.getDescription().isEmpty()){
            errorReport.addError("Description cannot be empty. ");
        }

        if (productDTO.getBarcode() == null || productDTO.getBarcode().isEmpty()){
            errorReport.addError("Barcode cannot be empty. ");
        }

        if (productDTO.getUnits() == null || productDTO.getUnits().isEmpty()){
            errorReport.addError("Units cannot be empty. ");
        }

        if (productRepository.findByBarcode(productDTO.getBarcode()) != null){
            errorReport.addError("Product with given barcode already exists, barcode must be unique. ");

        }


         if (productDTO.getId() != null){
             productDTO.setId(0L);
         }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateUpdate(ProductDTO productDTO){

        errorReport.clear();

        if (!productRepository.existsById(productDTO.getId())){
            errorReport.addError("Product with given id does not exist. ");
        }

        for (Product product: productRepository.findAll() ) {
            if(product.getDescription().equals(productDTO.getDescription())){
                if (product.getId() != productDTO.getId())
                errorReport.addError("Product with given description already exists. ");
            }
        }

        if (productRepository.findByBarcode(productDTO.getBarcode()) != null
                && productRepository.findByBarcode(productDTO.getBarcode()).getId() != productDTO.getId()){
            errorReport.addError("Product with given barcode already exists. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateDelete(Long id){

        errorReport.clear();

        if (!productRepository.existsById(id)){
            errorReport.addError("Product with given id does not exist. ");
        }

        if (!productShelfService.findByBarcode(productRepository.getById(id).getBarcode()).isEmpty()){
            errorReport.addError("Cannot delete a product while it is on stock. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateFindByBarcode(String barcode) {
        errorReport.clear();

        if (barcode.isEmpty()){
            errorReport.addError("Barcode cannot be empty. ");
        }

        if (productRepository.findByBarcode(barcode) == null){

            errorReport.addError("Product with given barcode does not exist. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }

    }

    public void validateGetStock(String barcode){
        errorReport.clear();

        if (productRepository.findByBarcode(barcode) == null){

            errorReport.addError("Product with given barcode does not exist. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }
}
