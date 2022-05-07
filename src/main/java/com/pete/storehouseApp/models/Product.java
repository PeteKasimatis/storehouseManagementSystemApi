package com.pete.storehouseApp.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "description")
    private String description;


    @Column(name = "barcode")
    private String barcode;


    @Column(name = "units")
    private String units;


    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL)
    private List<ProductShelf> productShelfList = new ArrayList<>();


    public Product() {
    }

    public Product(String description, String barcode, String units) {
        this.description = description;
        this.barcode = barcode;
        this.units = units;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", barcode='" + barcode + '\'' +
                ", units='" + units + '\'' +
                '}';
    }
}
