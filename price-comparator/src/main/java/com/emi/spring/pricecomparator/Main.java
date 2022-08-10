/**
 * -------------------------------------------------------------------------
 * Copyright (C) 2020 THALES ALENIA SPACE FRANCE. All rights reserved
 * -------------------------------------------------------------------------
 */
package com.emi.spring.pricecomparator;


import com.emi.spring.pricecomparator.domain.dao.ProductDAO;
import com.emi.spring.pricecomparator.domain.dao.ProductDAOImpl;
import com.emi.spring.pricecomparator.domain.service.PriceComparatorService;
import com.emi.spring.pricecomparator.domain.service.PriceComparatorServiceImpl;
import com.emi.spring.pricecomparator.domain.service.ProductService;
import com.emi.spring.pricecomparator.domain.service.ProductServiceImpl;
import com.emi.spring.pricecomparator.external.ExternalService;
import com.emi.spring.pricecomparator.external.ExternalServiceEmag;
import com.emi.spring.pricecomparator.facade.PriceCompareFacade;
import com.emi.spring.pricecomparator.facade.PriceCompareFacadeImpl;
import com.emi.spring.pricecomparator.facade.PriceUpdateFacade;
import com.emi.spring.pricecomparator.facade.PriceUpdateFacadeImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
//
//        ProductDAO productDAO = new ProductDAOImpl();
//        ExternalService externalService = new ExternalServiceEmag();
//
//        PriceComparatorService priceComparatorService = new PriceComparatorServiceImpl(productDAO,externalService);
//        PriceCompareFacade priceCompareFacade = new PriceCompareFacadeImpl(priceComparatorService);
//
//        ProductService productService = new ProductServiceImpl(productDAO);
//        PriceUpdateFacade priceUpdateFacade = new PriceUpdateFacadeImpl(productService);
//
//
//        Application application = new Application(priceCompareFacade,priceUpdateFacade);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Application application = (Application) context.getBean("application");
        application.run();
    }

}
