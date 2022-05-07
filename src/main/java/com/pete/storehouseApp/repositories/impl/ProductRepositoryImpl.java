package com.pete.storehouseApp.repositories.impl;

import com.pete.storehouseApp.dto.StockDTO;
import com.pete.storehouseApp.models.*;
import com.pete.storehouseApp.repositories.ProductRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Product findByBarcode(String barcode) {

        JPAQuery<Product> productJPAQuery = new JPAQuery<>(entityManager);

        QProduct qProduct = QProduct.product;

        Product product = productJPAQuery.select(qProduct)
                .from(qProduct)
                .where(qProduct.barcode.eq(barcode))
                .fetchOne();

        return product;
    }

    @Override
    public List<StockDTO> getStockByBarcodeByDate(Long productId, Date date) {

        QEntryRegistration qEntryRegistration = QEntryRegistration.entryRegistration;
        QExitRegistration qExitRegistration = QExitRegistration.exitRegistration;

        List<Tuple> entries = getSumOfEntries(productId, date);

        List<Tuple> exits = getSumOfExits(productId, date);

//        System.out.println(entries.toString());
//        System.out.println(exits.toString());

        List<StockDTO> stockDTOList = new ArrayList<>();

        //populate the list of stockDTO to be returned by subtracting the sum of quantities of exit registrations table
        //from the corresponding sum of the entry registrations for each shelf
        for (Tuple rowOfEntries : entries) {
            StockDTO stockDTO = new StockDTO(rowOfEntries.get(qEntryRegistration.shelf.identifier), rowOfEntries.get(qEntryRegistration.quantity.sum()));
            for (Tuple rowOfExits : exits) {
                if (rowOfEntries.get(qEntryRegistration.shelf.identifier).equals(rowOfExits.get(qExitRegistration.shelf.identifier))) {
                    stockDTO.setTotalQuantity(stockDTO.getTotalQuantity() - rowOfExits.get(qExitRegistration.quantity.sum()));
                }
            }

            stockDTOList.add(stockDTO);
        }

        return stockDTOList;
    }

    private List<Tuple> getSumOfEntries(Long productId, Date date) {
        JPAQuery<StockDTO> entryJPAQuery = new JPAQuery<>(entityManager);

        QEntryReceipt qEntryReceipt = QEntryReceipt.entryReceipt;
        QEntryRegistration qEntryRegistration = QEntryRegistration.entryRegistration;

        //fetch all entry registrations for given product until given date
        //and sum the quantity of those with the same shelf identifier
        List<Tuple> entries =
                entryJPAQuery
                        .select(qEntryRegistration.quantity.sum(), qEntryRegistration.shelf.identifier)
                        .from(qEntryRegistration)
                        .where(qEntryReceipt.dateOfEntry.loe(date).and(qEntryRegistration.product.id.eq(productId)))
                        .groupBy(qEntryRegistration.shelf.identifier)
                        .fetch();

        return entries;
    }

    private List<Tuple> getSumOfExits(Long productId, Date date) {
        JPAQuery<StockDTO> exitJPAQuery = new JPAQuery<>(entityManager);

        QExitReceipt qExitReceipt = QExitReceipt.exitReceipt;
        QExitRegistration qExitRegistration = QExitRegistration.exitRegistration;

        //fetch all exit registrations for given product until given date
        //and sum the quantity of those with the same shelf identifier
        List<Tuple> exits =
                exitJPAQuery
                        .select(qExitRegistration.quantity.sum(), qExitRegistration.shelf.identifier)
                        .from(qExitRegistration)
                        .where(qExitReceipt.dateOfExit.loe(date).and(qExitRegistration.product.id.eq(productId)))
                        .groupBy(qExitRegistration.shelf.identifier)
                        .fetch();

        return exits;
    }

}
