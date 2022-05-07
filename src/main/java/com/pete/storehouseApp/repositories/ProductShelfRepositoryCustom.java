package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.ProductShelf;

import java.util.List;

public interface ProductShelfRepositoryCustom {

    List<ProductShelf> findByBarcode(String barcode);

    List<ProductShelf> findByIdentifier(String identifier);


}
