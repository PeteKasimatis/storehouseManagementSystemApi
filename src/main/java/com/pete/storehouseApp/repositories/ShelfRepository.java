package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long>, ShelfRepositoryCustom {
}
