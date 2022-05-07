package com.pete.storehouseApp.services.impl;

import com.pete.storehouseApp.dto.ShelfDTO;
import com.pete.storehouseApp.models.Shelf;
import com.pete.storehouseApp.repositories.ShelfRepository;
import com.pete.storehouseApp.repositories.StorehouseRepository;
import com.pete.storehouseApp.services.ShelfService;
import com.pete.storehouseApp.validators.ShelfValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfServiceImpl implements ShelfService {

    @Autowired
    ShelfRepository shelfRepository;

    @Autowired
    StorehouseRepository storehouseRepository;

    @Autowired
    ShelfValidator shelfValidator;

    public Shelf dtoToEntity(ShelfDTO shelfDTO){

        Shelf shelf = new Shelf();

        BeanUtils.copyProperties(shelfDTO, shelf);


        return shelf;
    }

    public ShelfDTO entityToDTO(Shelf shelf){

        ShelfDTO shelfDTO = new ShelfDTO();

        BeanUtils.copyProperties(shelf, shelfDTO);

        //set the storehouseId for the shelfDTO
        shelfDTO.setStorehouseId(shelf.getStorehouse().getId());

        return shelfDTO;
    }


    @Override
    public ShelfDTO create(ShelfDTO shelfDTO) {
        //validate
        shelfValidator.validateCreate(shelfDTO);
        shelfValidator.validateStorehouseIdOnCreate(shelfDTO);

        //convert dto to entity
        Shelf shelf = dtoToEntity(shelfDTO);
        shelf.setStorehouse(storehouseRepository.getById(shelfDTO.getStorehouseId()));

        //save
        shelfRepository.save(shelf);

        return entityToDTO(shelf);
    }

    @Override
    public ShelfDTO update(ShelfDTO shelfDTO) {

        //validation
        shelfValidator.validateUpdate(shelfDTO);

        //get the shelf with the given id
        Shelf shelf = shelfRepository.getById(shelfDTO.getId());

        //set the identifier if we want to update it
        if (shelfDTO.getIdentifier() != null && !shelfDTO.getIdentifier().isEmpty()) {
            shelf.setIdentifier(shelfDTO.getIdentifier());
        }
        if (shelfDTO.getStorehouseId() != null){
            shelf.setStorehouse(storehouseRepository.getById(shelfDTO.getStorehouseId()));
        }

        //save the updated shelf
        shelfRepository.save(shelf);

        return entityToDTO(shelf);
    }

    @Override
    public List<ShelfDTO> findAll() {

        List<Shelf> shelfList = shelfRepository.findAll();

        List<ShelfDTO> shelfDTOList = new ArrayList<>();

        for (Shelf shelf: shelfList){
            shelfDTOList.add(entityToDTO(shelf));
        }

        return shelfDTOList;
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            shelfValidator.validateDelete(id);

            shelfRepository.deleteById(id);
        }
    }


    public ShelfDTO findByIdentifier(String identifier) {

        shelfValidator.validateFindByIdentifier(identifier);

        ShelfDTO shelfDTO = entityToDTO(shelfRepository.findByIdentifier(identifier));

        return shelfDTO;
    }
}
