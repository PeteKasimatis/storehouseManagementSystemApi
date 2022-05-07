package com.pete.storehouseApp.dto;

public class StockDTO {

    String shelfIdentifier;

    Double totalQuantity;

    public StockDTO(String shelfIdentifier, Double totalQuantity) {
        this.shelfIdentifier = shelfIdentifier;
        this.totalQuantity = totalQuantity;
    }

    public String getShelfIdentifier() {
        return shelfIdentifier;
    }

    public void setShelfIdentifier(String shelfIdentifier) {
        this.shelfIdentifier = shelfIdentifier;
    }

    public Double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
