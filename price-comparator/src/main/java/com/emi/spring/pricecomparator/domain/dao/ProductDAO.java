/**
 * -------------------------------------------------------------------------
 * Copyright (C) 2020 THALES ALENIA SPACE FRANCE. All rights reserved
 * -------------------------------------------------------------------------
 */
package com.emi.spring.pricecomparator.domain.dao;

import com.emi.spring.pricecomparator.domain.entity.ProductEntity;

public interface ProductDAO {
    ProductEntity getProductByName(String name);
    void updateProduct(ProductEntity productEntity);
}
