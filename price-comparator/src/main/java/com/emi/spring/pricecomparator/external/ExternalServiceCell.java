package com.emi.spring.pricecomparator.external;

public class ExternalServiceCell implements ExternalService{
    @Override
    public float getPriceForProduct(String productName) {
        return 60;
    }
}
