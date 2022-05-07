package com.pete.storehouseApp.services.impl;

import com.pete.storehouseApp.dto.ShelfDTO;
import com.pete.storehouseApp.dto.StorehouseDTO;
import com.pete.storehouseApp.models.Shelf;
import com.pete.storehouseApp.models.Storehouse;
import com.pete.storehouseApp.repositories.StorehouseRepository;
import com.pete.storehouseApp.services.ShelfService;
import com.pete.storehouseApp.services.StorehouseService;
import com.pete.storehouseApp.validators.ShelfValidator;
import com.pete.storehouseApp.validators.StorehouseValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorehouseServiceImpl implements StorehouseService {

    @Autowired
    ShelfService shelfService;

    @Autowired
    StorehouseValidator storehouseValidator;

    @Autowired
    ShelfValidator shelfValidator;

    @Autowired
    StorehouseRepository storehouseRepository;

    private Storehouse dtoToEntity(StorehouseDTO storehouseDTO){

        Storehouse storehouse = new Storehouse();

        BeanUtils.copyProperties(storehouseDTO, storehouse);

        storehouse.setShelves(fillStorehouseShelfList(storehouseDTO, storehouse));

        return storehouse;
    }

    private StorehouseDTO entityToDTO(Storehouse storehouse){

        StorehouseDTO storehouseDTO = new StorehouseDTO();

        BeanUtils.copyProperties(storehouse, storehouseDTO);

        return storehouseDTO;
    }

    private List<Shelf> fillStorehouseShelfList(StorehouseDTO storehouseDTO, Storehouse storehouse) {

        List<Shelf> shelves = new ArrayList<>();

        for (ShelfDTO shelfDTO : storehouseDTO.getShelves()){

            shelfValidator.validateCreate(shelfDTO);

            Shelf shelf = shelfService.dtoToEntity(shelfDTO);
            shelf.setStorehouse(storehouse);
            shelves.add(shelf);
        }

        return shelves;
    }


        public StorehouseDTO create(StorehouseDTO storehouseDTO) {


        storehouseValidator.validateCreate(storehouseDTO);

        Storehouse storehouse = dtoToEntity(storehouseDTO);

        storehouseRepository.save(storehouse);

        return entityToDTO(storehouse);
    }

    public StorehouseDTO update(StorehouseDTO storehouseDTO) {

        storehouseValidator.validateUpdate(storehouseDTO);

        Storehouse storehouse = storehouseRepository.getById(storehouseDTO.getId());

        if (storehouseDTO.getDescription() != null){
            storehouse.setDescription(storehouseDTO.getDescription());
        }

        if (storehouseDTO.getShelves() != null){
            List<Shelf> shelves = new ArrayList<>();

            //fill storehouse shelves
            for (ShelfDTO shelfDTO : storehouseDTO.getShelves()){

                if (shelfDTO.getId() == null){
                    shelfValidator.validateCreate(shelfDTO);
                    shelfDTO.setStorehouseId(storehouseDTO.getId());
                    shelfService.create(shelfDTO);
                }
                else {
                    shelfValidator.validateUpdate(shelfDTO);
                    shelfService.update(shelfDTO);
                }
            }
        }
        storehouseRepository.save(storehouse);
        return entityToDTO(storehouse);
    }


    public List<StorehouseDTO> findAll(){

        List<Storehouse> storehouseList = this.storehouseRepository.findAll();

        List<StorehouseDTO> storehouseDTOList = new ArrayList<>();

        for (Storehouse storehouse: storehouseList){
            storehouseDTOList.add(entityToDTO(storehouse));
        }

        return storehouseDTOList;
    }


    public void delete(Long id){

            storehouseValidator.validateDelete(id);

            storehouseRepository.deleteById(id);

    }

    public List<ShelfDTO> findAllShelves(Long id){

        storehouseValidator.validateFindAllShelves(id);

        Storehouse storehouse = storehouseRepository.getById(id);

        List<Shelf> shelfList = storehouse.getShelves();
        List<ShelfDTO> shelfDTOList = new ArrayList<>();

        for (Shelf shelf: shelfList){
            shelfDTOList.add(shelfService.entityToDTO(shelf));
        }
        return shelfDTOList;
    }

    public StorehouseDTO findById(Long id){

        storehouseValidator.validateFindAllShelves(id);

        Storehouse storehouse = storehouseRepository.getById(id);

        return entityToDTO(storehouse);
    }
}
