package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.Shelf;

public interface ShelfRepositoryCustom {

    Shelf findByIdentifier(String identifier);
}
