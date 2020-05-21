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

}
