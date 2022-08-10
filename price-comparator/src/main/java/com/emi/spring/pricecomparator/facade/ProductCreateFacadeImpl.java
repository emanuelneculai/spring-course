package com.emi.spring.pricecomparator.facade;

import com.emi.spring.pricecomparator.domain.entity.ProductEntity;
import com.emi.spring.pricecomparator.domain.service.ProductService;

public class ProductCreateFacadeImpl implements ProductCreateFacade {

    private ProductService productService;

    public ProductCreateFacadeImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductEntity createProduct(String productName, float price) {
        return productService.createProduct(productName,price);
    }
}
