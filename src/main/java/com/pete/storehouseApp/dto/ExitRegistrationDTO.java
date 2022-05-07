package com.pete.storehouseApp.dto;

public class ExitRegistrationDTO {

    private Long id;

    private Double quantity;

    private String productBarcode;

    private String shelfIdentifier;

    private Long exitReceiptId;

    public ExitRegistrationDTO(){

    }

    public ExitRegistrationDTO(Double quantity, String productBarcode, String shelfIdentifier) {
        this.quantity = quantity;
        this.productBarcode = productBarcode;
        this.shelfIdentifier = shelfIdentifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

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

    public Long getExitReceiptId() {
        return exitReceiptId;
    }

    public void setExitReceiptId(Long exitReceiptId) {
        this.exitReceiptId = exitReceiptId;
    }
}
