package com.pete.storehouseApp.repositories;

import com.pete.storehouseApp.models.ExitRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExitRegistrationRepository extends JpaRepository<ExitRegistration, Long>, ExitRegistrationRepositoryCustom {

}
