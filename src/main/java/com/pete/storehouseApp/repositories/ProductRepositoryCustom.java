package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.dto.StockDTO;
import com.pete.storehouseApp.models.Product;

import java.util.Date;
import java.util.List;

public interface ProductRepositoryCustom {

    Product findByBarcode(String barcode);

    List<StockDTO> getStockByBarcodeByDate(Long productId, Date date);
}
