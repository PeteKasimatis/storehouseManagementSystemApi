package com.pete.storehouseApp.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductShelfId implements Serializable {

    private Long product;

    private Long shelf;

    public ProductShelfId(){

    }

    public ProductShelfId(Long productId, Long shelfId) {
        this.product = productId;
        this.shelf = shelfId;
    }

    public Long getProductId() {
        return product;
    }

    public void setProductId(Long productId) {
        this.product = product;
    }

    public Long getShelfId() {
        return shelf;
    }

    public void setShelfId(Long shelf) {
        this.shelf = shelf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ProductShelfId that = (ProductShelfId) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(shelf, that.shelf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, shelf);
    }
}
