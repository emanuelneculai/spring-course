/**
 * -------------------------------------------------------------------------
 * Copyright (C) 2020 THALES ALENIA SPACE FRANCE. All rights reserved
 * -------------------------------------------------------------------------
 */
package com.emi.spring.pricecomparator.domain.service;

import com.emi.spring.pricecomparator.domain.HibernateUtil;
import com.emi.spring.pricecomparator.domain.dao.ProductDAO;
import com.emi.spring.pricecomparator.domain.dao.ProductDAOImpl;
import com.emi.spring.pricecomparator.domain.entity.ProductEntity;
import com.emi.spring.pricecomparator.domain.model.ComparisonResult;
import com.emi.spring.pricecomparator.external.ExternalService;
import com.emi.spring.pricecomparator.external.ExternalServiceCell;
import com.emi.spring.pricecomparator.external.ExternalServiceEmag;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;

public class PriceComparatorServiceImpl implements PriceComparatorService {

    private ProductDAO productDAO;
    private ExternalService externalService;

    public PriceComparatorServiceImpl(ProductDAO productDAO,ExternalService externalService) {
        this.productDAO = productDAO;
        this.externalService = externalService;
    }

    public ComparisonResult comparePriceFor(String product) throws NoRecordFoundException {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = session.beginTransaction();

        try {
            ProductEntity persistentProductEntity = productDAO.getProductByName(product);
            float externalProductPrice = externalService.getPriceForProduct(product);
            if(persistentProductEntity == null) {
                throw new NoRecordFoundException();
            }
            return new ComparisonResult(persistentProductEntity.getPrice(),externalProductPrice);
        } catch (Exception e){
            tx.rollback();
            if(!(e instanceof NoRecordFoundException))
                throw new RuntimeException();
            else throw (NoRecordFoundException)e;
        } finally {
            try {
                session.close();
            } catch (SessionException e) {

            }
        }

    }

}
