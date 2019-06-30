package com.javaguru.SoloShoppingList.console.action;

import com.javaguru.SoloShoppingList.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintAllProductsMenu implements MenuItem {

    private ProductService productService;

    @Autowired
    public PrintAllProductsMenu(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void action() {
        productService.printAll();
    }
}
