/**
 * -------------------------------------------------------------------------
 * Copyright (C) 2020 THALES ALENIA SPACE FRANCE. All rights reserved
 * -------------------------------------------------------------------------
 */
package com.emi.spring.pricecomparator.domain.dao;

import com.emi.spring.pricecomparator.domain.HibernateUtil;
import com.emi.spring.pricecomparator.domain.entity.ProductEntity;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProductDAOImpl implements ProductDAO {

    public ProductEntity getProductByName(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("Select p FROM ProductEntity p where p.productName = :productName");
        query.setParameter("productName",name);
        ProductEntity persistentProductEntity = (ProductEntity) query.uniqueResult();
        return persistentProductEntity;
    }

    public void updateProduct(ProductEntity productEntity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.saveOrUpdate(productEntity);
    }

    @Override
    public ProductEntity createProduct(String name, float price) {
        ProductEntity productEntity = new ProductEntity(name, price);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Long id = (Long) session.save(productEntity);
        return session.get(ProductEntity.class, id);
    }

}
