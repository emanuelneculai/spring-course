/**
 * -------------------------------------------------------------------------
 * Copyright (C) 2020 THALES ALENIA SPACE FRANCE. All rights reserved
 * -------------------------------------------------------------------------
 */
package com.emi.spring.pricecomparator.domain.service;

import com.emi.spring.pricecomparator.domain.entity.ProductEntity;

public interface ProductService {

    void updatePriceForProduct (String productName, float newPrice);

    ProductEntity createProduct(String productName, float price);
}
