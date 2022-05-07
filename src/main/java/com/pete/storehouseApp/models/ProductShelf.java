package com.pete.storehouseApp.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_shelf")
@IdClass(ProductShelfId.class)
public class ProductShelf {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelf_id", referencedColumnName = "id")
    private Shelf shelf;

    @Column(name = "quantity")
    private Double quantity;

    public ProductShelf(){

    }

    public ProductShelf(Product product, Shelf shelf) {
        this.product = product;
        this.shelf = shelf;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ProductShelf that = (ProductShelf) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(shelf, that.shelf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, shelf);
    }
}
