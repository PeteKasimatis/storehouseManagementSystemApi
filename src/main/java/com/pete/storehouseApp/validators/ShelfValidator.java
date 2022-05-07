package com.pete.storehouseApp.validators;

import com.pete.storehouseApp.dto.ShelfDTO;
import com.pete.storehouseApp.errorHandling.errorReport.ErrorReport;
import com.pete.storehouseApp.models.Shelf;
import com.pete.storehouseApp.repositories.ProductShelfRepository;
import com.pete.storehouseApp.repositories.ShelfRepository;
import com.pete.storehouseApp.repositories.StorehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelfValidator {

    @Autowired
    ShelfRepository shelfRepository;

    @Autowired
    private StorehouseRepository storehouseRepository;

    @Autowired
    private ProductShelfRepository productShelfRepository;

    @Autowired
    ErrorReport errorReport;

    public void validateCreate(ShelfDTO shelfDTO){

        errorReport.clear();

        //error if identifier is empty
        if (shelfDTO.getIdentifier().isEmpty() || shelfDTO.getIdentifier() == null){
            errorReport.addError("Shelf identifier cannot be empty. ");
        }

        if (shelfRepository.findByIdentifier(shelfDTO.getIdentifier()) != null){
            errorReport.addError("Shelf with given identifier already exists, identifier must be unique. ");

        }

        //if id is not null explicitly set it to 0
        //id is auto incremental
        if (shelfDTO.getId() != null){
            shelfDTO.setId(0L);
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateStorehouseIdOnCreate(ShelfDTO shelfDTO){
        errorReport.clear();

        if (!storehouseRepository.existsById(shelfDTO.getStorehouseId())){
            errorReport.addError("Storehouse with given id does not exist. ");

        }

        if (shelfDTO.getStorehouseId() == null){
            errorReport.addError("Storehouse id cannot be empty. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateUpdate(ShelfDTO shelfdto){

        errorReport.clear();

        if (shelfRepository.existsById(shelfdto.getId()) == false){

            errorReport.addError("Shelf with given id does not exist. ");
        }
        for (Shelf shelf: shelfRepository.findAll() ) {
            if(shelf.getIdentifier().equals(shelfdto.getIdentifier())){
                if (shelf.getId() != shelfdto.getId())
                    errorReport.addError("Shelf with given identifier already exists. ");
            }
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }

    }

    public void validateDelete(Long id){

        errorReport.clear();

        if (shelfRepository.existsById(id) == false){

            errorReport.addError("Shelf with given id does not exist. ");
        }

        if (!productShelfRepository.findByIdentifier(shelfRepository.getById(id).getIdentifier()).isEmpty()){
            errorReport.addError("Cannot delete a shelf that is not empty. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateFindByIdentifier(String identifier){
        errorReport.clear();

        if (identifier.isEmpty()){
            errorReport.addError("Identifier cannot be empty. ");
        }

        if (shelfRepository.findByIdentifier(identifier) == null){

            errorReport.addError("Shelf with given identifier does not exist. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }
}
