package com.pete.storehouseApp.repositories.impl;

import com.pete.storehouseApp.models.EntryRegistration;
import com.pete.storehouseApp.models.QEntryRegistration;
import com.pete.storehouseApp.repositories.EntryRegistrationRepositoryCustom;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class EntryRegistrationRepositoryImpl implements EntryRegistrationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteByReceiptId(Long id) {
        JPAQuery<EntryRegistration> entryRegistrationJPAQuery = new JPAQuery<>(entityManager);

        QEntryRegistration qEntryRegistration = QEntryRegistration.entryRegistration;

        new JPADeleteClause(entityManager, qEntryRegistration)
                .where(qEntryRegistration.entryReceipt.id.eq(id))
                .execute();
    }

    @Override
    public List<EntryRegistration> getByReceiptId(Long id) {
        JPAQuery<EntryRegistration> entryRegistrationJPAQuery = new JPAQuery<>(entityManager);

        QEntryRegistration qEntryRegistration = QEntryRegistration.entryRegistration;

        List<EntryRegistration> entryRegistrationList = entryRegistrationJPAQuery.select(qEntryRegistration)
            .from(qEntryRegistration)
                .where(qEntryRegistration.entryReceipt.id.eq(id))
                .fetch();

        return entryRegistrationList;
    }
}