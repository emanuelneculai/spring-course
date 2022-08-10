/**
 * -------------------------------------------------------------------------
 * Copyright (C) 2020 THALES ALENIA SPACE FRANCE. All rights reserved
 * -------------------------------------------------------------------------
 */
package com.emi.spring.pricecomparator;

import com.emi.spring.pricecomparator.domain.entity.ProductEntity;
import com.emi.spring.pricecomparator.domain.model.ComparisonResult;
import com.emi.spring.pricecomparator.domain.service.NoRecordFoundException;
import com.emi.spring.pricecomparator.facade.*;

import java.util.Arrays;
import java.util.Scanner;

public class Application {

    private PriceCompareFacade priceCompareFacade;
    private PriceUpdateFacade priceUpdateFacade;
    private ProductCreateFacade productCreateFacade;

    Application(PriceCompareFacade priceCompareFacade, PriceUpdateFacade priceUpdateFacade, ProductCreateFacade productCreateFacade) throws ClassNotFoundException {
        this.priceCompareFacade = priceCompareFacade;
        this.priceUpdateFacade = priceUpdateFacade;
        this.productCreateFacade = productCreateFacade;
        Class myclass  = Class.forName("com.emi.spring.pricecomparator.domain.HibernateUtil");
    }

    public void run() {
        while (true) {
            System.out.println("Choose an option : ");
            for (UserActionOptions option : UserActionOptions.values())
                System.out.println(option.getNr() + option.getText());
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();
            UserActionOptions userActionOption = UserActionOptions.getEnumFromNr(userInput);
            if (userActionOption != null) {
                switch (userActionOption) {
                    //TODO - case SEE_ALL_PRODUCTS
                    case COMPARE:
                        // TODO - in loc de Arrays.asList(Product.values) -> GetProductFacade.getAllProducts()
                        System.out.println("Choose a product : " + Arrays.asList(Product.values()));
                        // TODO - in loc de numele produsului, luam idul lui
                        String productToCompare = scanner.next();
                        try {
                            ComparisonResult comparisonResult = priceCompareFacade.comparePrice(productToCompare);
                            System.out.println("Our price : " + comparisonResult.getOurPrice() + " Their price : " + comparisonResult.getTheirPrice());
                        } catch (NoRecordFoundException e) {
                            System.out.println("Product not found in DB");
                        }
                        break;
                    case UPDATE:
                        // TODO - in loc de Arrays.asList(Product.values) -> GetProductFacade.getAllProducts()
                        System.out.println("Choose a product : " + Arrays.asList(Product.values()));
                        // TODO - in loc de numele produsului, luam idul lui
                        String productToUpdate = scanner.next();
                        System.out.println("Enter the new price :");
                        float newPrice = scanner.nextFloat();
                        priceUpdateFacade.updatePriceForProduct(productToUpdate,newPrice);
                        System.out.println("Product updated");
                        break;

                    case CREATE:
                        System.out.println("Enter product name : ");
                        String productName = scanner.next();
                        System.out.println("Enter the price :");
                        float price = scanner.nextFloat();
                        ProductEntity createdProduct = productCreateFacade.createProduct(productName,price);
                        System.out.println("Product created : id = " +  createdProduct.getId() + " name = " + createdProduct.getProductName() + " price = " + createdProduct.getPrice());
                        break;
                }

            }
        }
    }

}
