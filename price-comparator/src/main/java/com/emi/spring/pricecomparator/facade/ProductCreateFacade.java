package com.emi.spring.pricecomparator.facade;

import com.emi.spring.pricecomparator.domain.entity.ProductEntity;

public interface ProductCreateFacade {
    ProductEntity createProduct(String productName, float price);
}
