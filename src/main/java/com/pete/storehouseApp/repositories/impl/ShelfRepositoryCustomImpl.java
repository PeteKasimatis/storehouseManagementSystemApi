package com.pete.storehouseApp.repositories.impl;

import com.pete.storehouseApp.models.QShelf;
import com.pete.storehouseApp.models.Shelf;
import com.pete.storehouseApp.repositories.ShelfRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ShelfRepositoryCustomImpl implements ShelfRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Shelf findByIdentifier(String identifier) {

        JPAQuery<Shelf> shelfJPAQuery = new JPAQuery<>(entityManager);

        QShelf qShelf = QShelf.shelf;

        Shelf shelf = shelfJPAQuery.select(qShelf)
                .from(qShelf)
                .where(qShelf.identifier.eq(identifier))
                .fetchOne();

        return shelf;
    }

}
