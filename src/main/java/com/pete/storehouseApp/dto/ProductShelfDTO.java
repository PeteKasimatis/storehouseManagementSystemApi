package com.pete.storehouseApp.dto;

public class ProductShelfDTO {

    private String productBarcode;

    private String shelfIdentifier;

    private Double quantity;

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public String getShelfIdentifier() {
        return shelfIdentifier;
    }

    public void setShelfIdentifier(String shelfIdentifier) {
        this.shelfIdentifier = shelfIdentifier;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
