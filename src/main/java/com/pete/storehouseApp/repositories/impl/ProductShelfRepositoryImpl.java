package com.pete.storehouseApp.repositories.impl;

import com.pete.storehouseApp.models.ProductShelf;
import com.pete.storehouseApp.models.QProductShelf;
import com.pete.storehouseApp.repositories.ProductShelfRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductShelfRepositoryImpl implements ProductShelfRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ProductShelf> findByBarcode(String barcode) {

        JPAQuery<ProductShelf> productShelfJPAQuery = new JPAQuery<>(entityManager);

        QProductShelf qProductShelf = QProductShelf.productShelf;

        List<ProductShelf> productShelfList = productShelfJPAQuery.select(qProductShelf)
                .from(qProductShelf)
                .where(qProductShelf.product.barcode.eq(barcode))
                .fetch();

        return productShelfList;
    }

    @Override
    public List<ProductShelf> findByIdentifier(String identifier) {

        JPAQuery<ProductShelf> productShelfJPAQuery = new JPAQuery<>(entityManager);

        QProductShelf qProductShelf = QProductShelf.productShelf;

        List<ProductShelf> productShelfList = productShelfJPAQuery.select(qProductShelf)
                .from(qProductShelf)
                .where(qProductShelf.shelf.identifier.eq(identifier))
                .fetch();

        return productShelfList;
    }
}
