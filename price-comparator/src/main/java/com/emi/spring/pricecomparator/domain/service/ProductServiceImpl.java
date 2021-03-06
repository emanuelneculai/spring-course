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
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;

public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    public ProductServiceImpl() {
        this.productDAO = new ProductDAOImpl();
    }

    public void updatePriceForProduct(String productName, float newPrice) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = session.beginTransaction();
        try {
            ProductEntity productEntity = productDAO.getProductByName(productName);
            productEntity.setPrice(newPrice);
            productDAO.updateProduct(productEntity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException();
        } finally {
            try {
                session.close();
            } catch (SessionException e) {

            }
        }

    }
}
