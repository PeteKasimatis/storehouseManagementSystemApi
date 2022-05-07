package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.EntryRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRegistrationRepository extends JpaRepository<EntryRegistration, Long>, EntryRegistrationRepositoryCustom {


}
