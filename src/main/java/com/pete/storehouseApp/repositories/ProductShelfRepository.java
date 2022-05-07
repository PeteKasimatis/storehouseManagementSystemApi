package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.ProductShelf;
import com.pete.storehouseApp.models.ProductShelfId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductShelfRepository extends JpaRepository<ProductShelf, ProductShelfId>, ProductShelfRepositoryCustom {
}
