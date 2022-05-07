package com.pete.storehouseApp.services;

import com.pete.storehouseApp.dto.ProductDTO;
import com.pete.storehouseApp.dto.StockDTO;

import java.util.Date;
import java.util.List;

public interface ProductService {

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(ProductDTO productDTO);

    List<ProductDTO> findAll();

    void delete(Long id);

    ProductDTO findByBarcode(String barcode);

    List<StockDTO> getStockByBarcodeByDate(String barcode, Date date);
}
