package com.pete.storehouseApp.dto;

import java.util.List;

public class StorehouseDTO {

    private Long id;

    private String description;

    private List<ShelfDTO> shelves;

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

    public List<ShelfDTO> getShelves() {
        return shelves;
    }

    public void setShelves(List<ShelfDTO> shelves) {
        this.shelves = shelves;
    }
}
