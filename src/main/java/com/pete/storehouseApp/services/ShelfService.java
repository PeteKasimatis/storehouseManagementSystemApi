package com.pete.storehouseApp.services;

import com.pete.storehouseApp.dto.ShelfDTO;
import com.pete.storehouseApp.models.Shelf;

import java.util.List;

public interface ShelfService {

    ShelfDTO entityToDTO(Shelf shelf);

    Shelf dtoToEntity(ShelfDTO shelfDTO);

    ShelfDTO create(ShelfDTO shelfDTO);

    ShelfDTO update(ShelfDTO shelfDTO);

    List<ShelfDTO> findAll();

    void delete(Long id);

    ShelfDTO findByIdentifier(String identifier);
}
