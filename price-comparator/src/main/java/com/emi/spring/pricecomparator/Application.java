/**
 * -------------------------------------------------------------------------
 * Copyright (C) 2020 THALES ALENIA SPACE FRANCE. All rights reserved
 * -------------------------------------------------------------------------
 */
package com.emi.spring.pricecomparator;

import com.emi.spring.pricecomparator.domain.model.ComparisonResult;
import com.emi.spring.pricecomparator.domain.service.NoRecordFoundException;
import com.emi.spring.pricecomparator.facade.PriceCompareFacade;
import com.emi.spring.pricecomparator.facade.PriceCompareFacadeImpl;
import com.emi.spring.pricecomparator.facade.PriceUpdateFacade;
import com.emi.spring.pricecomparator.facade.PriceUpdateFacadeImpl;
import com.emi.spring.pricecomparator.facade.Product;
import com.emi.spring.pricecomparator.facade.UserActionOptions;

import java.util.Arrays;
import java.util.Scanner;

public class Application {

    private PriceCompareFacade priceCompareFacade;
    private PriceUpdateFacade priceUpdateFacade;

    Application() {
        this.priceCompareFacade = new PriceCompareFacadeImpl();
        this.priceUpdateFacade = new PriceUpdateFacadeImpl();
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
                    case COMPARE:
                        System.out.println("Choose a product : " + Arrays.asList(Product.values()));
                        String productToCompare = scanner.next();
                        try {
                            ComparisonResult comparisonResult = priceCompareFacade.comparePrice(productToCompare);
                            System.out.println("Our price : " + comparisonResult.getOurPrice() + " Their price : " + comparisonResult.getTheirPrice());
                        } catch (NoRecordFoundException e) {
                            System.out.println("Product not found in DB");
                        }
                        break;
                    case UPDATE:
                        System.out.println("Choose a product : " + Arrays.asList(Product.values()));
                        String productToUpdate = scanner.next();
                        System.out.println("Enter the new price :");
                        float newPrice = scanner.nextFloat();
                        priceUpdateFacade.updatePriceForProduct(productToUpdate,newPrice);
                        System.out.println("Product updated");
                }

            }
        }
    }

}
