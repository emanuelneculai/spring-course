/**
 * -------------------------------------------------------------------------
 * Copyright (C) 2020 THALES ALENIA SPACE FRANCE. All rights reserved
 * -------------------------------------------------------------------------
 */
package com.emi.spring.pricecomparator;

import com.emi.spring.pricecomparator.domain.model.ComparisonResult;
import com.emi.spring.pricecomparator.domain.service.NoRecordFoundException;
import com.emi.spring.pricecomparator.facade.IncorectProductException;
import com.emi.spring.pricecomparator.facade.PriceCompareFacade;
import com.emi.spring.pricecomparator.facade.PriceCompareFacadeImpl;
import com.emi.spring.pricecomparator.facade.Product;

import java.util.Arrays;
import java.util.Scanner;

public class Application {

    PriceCompareFacade priceCompareFacade;

    Application() {
        this.priceCompareFacade = new PriceCompareFacadeImpl();
    }

    public void run() {
        while (true) {
            System.out.println("Choose a product from : " + Arrays.asList(Product.values()));
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.next();
            try {
                ComparisonResult comparisonResult = priceCompareFacade.comparePrice(userInput);
                System.out.println("Our price : " + comparisonResult.getOurPrice() + " Their price : " + comparisonResult.getTheirPrice());
            } catch (IncorectProductException e) {
            } catch (NoRecordFoundException e) {
                System.out.println("Product not found in DB");
            }
        }
    }

}
