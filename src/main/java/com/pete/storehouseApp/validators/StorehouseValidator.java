package com.pete.storehouseApp.validators;

import com.pete.storehouseApp.dto.ShelfDTO;
import com.pete.storehouseApp.dto.StorehouseDTO;
import com.pete.storehouseApp.errorHandling.errorReport.ErrorReport;
import com.pete.storehouseApp.repositories.ProductShelfRepository;
import com.pete.storehouseApp.repositories.StorehouseRepository;
import com.pete.storehouseApp.services.StorehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorehouseValidator {

    @Autowired
    StorehouseRepository storehouseRepository;

    @Autowired
    StorehouseService storehouseService;

    @Autowired
    ProductShelfRepository productShelfRepository;

    @Autowired
    ErrorReport errorReport;

    public void validateCreate(StorehouseDTO storehouseDTO) {

        errorReport.clear();

        if (storehouseDTO.getId() != null) {
            storehouseDTO.setId(0L);
        }

        if (storehouseDTO.getDescription().isEmpty() || storehouseDTO.getDescription() == null) {

            errorReport.addError("Description cannot be empty. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateUpdate(StorehouseDTO storehouseDTO) {

        errorReport.clear();

        if (storehouseDTO.getId() == null) {
            errorReport.addError("Storehouse id cannot be null. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateDelete(Long id){
        errorReport.clear();

        if (storehouseRepository.existsById(id) == false){
            errorReport.addError("Storehouse with given id does not exist. ");
        }

        for(ShelfDTO shelfDTO: storehouseService.findAllShelves(id)) {
            if (!productShelfRepository.findByIdentifier(shelfDTO.getIdentifier()).isEmpty()){
                errorReport.addError("Shelf " + shelfDTO.getIdentifier() + " is not empty . ");
            }
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateFindAllShelves(Long id){

        errorReport.clear();

        if (storehouseRepository.existsById(id) == false){
            errorReport.addError("Storehouse with given id does not exist. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }
}
