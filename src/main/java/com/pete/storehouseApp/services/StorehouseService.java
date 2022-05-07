package com.pete.storehouseApp.services;

import com.pete.storehouseApp.dto.ShelfDTO;
import com.pete.storehouseApp.dto.StorehouseDTO;

import java.util.List;

public interface StorehouseService {

    //create a storehouse
    StorehouseDTO create(StorehouseDTO storehouseDTO);

    //update a storehouse
    StorehouseDTO update(StorehouseDTO storehouseDTO);

    //get all storehouses
    List<StorehouseDTO> findAll();

    //delete a storehouse
    void delete(Long id);

    //get all shelves of given storehouse
    public List<ShelfDTO> findAllShelves(Long id);

    public StorehouseDTO findById(Long id);
}
