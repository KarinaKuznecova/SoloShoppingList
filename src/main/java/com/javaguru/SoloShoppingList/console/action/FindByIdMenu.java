package com.javaguru.SoloShoppingList.console.action;

import com.javaguru.SoloShoppingList.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindByIdMenu implements MenuItem {
    private Reader reader = new Reader();
    private ProductService productService;

    @Autowired
    public FindByIdMenu(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void action() {
        long id = reader.getUserInput("Enter product id: ");
        System.out.println(productService.getProductById(id));
    }
}
