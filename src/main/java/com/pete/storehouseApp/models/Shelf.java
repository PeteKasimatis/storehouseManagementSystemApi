package com.pete.storehouseApp.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shelf")
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "identifier")
    private String identifier;


    /*
        Bidirectional ManyToOne relation
        Many shelves can exist in a storehouse
     */
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "storehouse_id")
    private Storehouse storehouse;


    @OneToMany(mappedBy = "shelf",
            cascade = CascadeType.ALL)
    private List<ProductShelf> productShelfList = new ArrayList<>();


    public Shelf() {
    }

    public Shelf(String identifier, Storehouse storehouse) {
        this.identifier = identifier;
        this.storehouse = storehouse;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Storehouse getStorehouse() {
        return storehouse;
    }

    public void setStorehouse(Storehouse storehouse) {
        this.storehouse = storehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Shelf{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", storehouse=" + storehouse +
                '}';
    }
}
