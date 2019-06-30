package com.javaguru.SoloShoppingList.console.action;

import com.javaguru.SoloShoppingList.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteByIdMenu implements MenuItem {
    private Reader reader = new Reader();
    ProductService productService;

    @Autowired
    public DeleteByIdMenu(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void action() {
        long id = reader.getUserInput("Enter product id: ");
        productService.deleteById(id);
    }
}