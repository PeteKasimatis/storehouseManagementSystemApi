package com.pete.storehouseApp.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "storehouse")
public class Storehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    /*
    Bidirectional ManyToOne relation
    Many shelves can exist in a storehouse
    */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storehouse")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Shelf> shelves ;


    public Storehouse() {
    }

    public Storehouse(String description) {
        this.description = description;
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

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }


    @Override
    public String toString() {
        return "Storehouse{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
