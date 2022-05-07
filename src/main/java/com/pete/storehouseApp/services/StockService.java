package com.pete.storehouseApp.services;

import com.pete.storehouseApp.dto.StockDTO;

import java.util.Date;
import java.util.List;

public interface StockService {

    List<StockDTO> getStockByBarcodeByDate(String barcode, Date date);

}
