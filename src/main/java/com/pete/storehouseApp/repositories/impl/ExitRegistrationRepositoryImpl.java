package com.pete.storehouseApp.repositories.impl;

import com.pete.storehouseApp.models.ExitRegistration;
import com.pete.storehouseApp.models.QExitRegistration;
import com.pete.storehouseApp.repositories.ExitRegistrationRepositoryCustom;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ExitRegistrationRepositoryImpl implements ExitRegistrationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteByReceiptId(Long id) {
        JPAQuery<ExitRegistration> exitRegistrationJPAQuery = new JPAQuery<>(entityManager);

        QExitRegistration qExitRegistration = QExitRegistration.exitRegistration;

        new JPADeleteClause(entityManager, qExitRegistration)
                .where(qExitRegistration.exitReceipt.id.eq(id))
                .execute();
    }

    @Override
    public List<ExitRegistration> getByReceiptId(Long id) {
        JPAQuery<ExitRegistration> exitRegistrationJPAQuery = new JPAQuery<>(entityManager);

        QExitRegistration qExitRegistration = QExitRegistration.exitRegistration;

        List<ExitRegistration> exitRegistrationList = exitRegistrationJPAQuery.select(qExitRegistration)
                .from(qExitRegistration)
                .where(qExitRegistration.exitReceipt.id.eq(id))
                .fetch();

        return exitRegistrationList;
    }
}
