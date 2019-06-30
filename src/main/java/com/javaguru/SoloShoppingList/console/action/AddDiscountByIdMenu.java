package com.javaguru.SoloShoppingList.console.action;

import com.javaguru.SoloShoppingList.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AddDiscountByIdMenu implements MenuItem {
    private Reader reader = new Reader();
    private ProductService productService;

    @Autowired
    public AddDiscountByIdMenu(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void action() {
        long id = reader.getUserInput("Enter product id: ");
        BigDecimal discount = new BigDecimal(reader.getUserInputDouble("Enter discount: "));
        productService.setDiscountById(id, discount);
    }
}
