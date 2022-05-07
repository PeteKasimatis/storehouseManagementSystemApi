package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.Storehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorehouseRepository extends JpaRepository<Storehouse, Long> , StorehouseRepositoryCustom{

}
