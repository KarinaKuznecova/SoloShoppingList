package com.javaguru.console;

import com.javaguru.service.ProductService;

import java.math.BigDecimal;

class AddDiscountByIdMenu implements MenuItem {
    private Reader reader = new Reader();

    @Override
    public void action(ProductService productService) {
        long id = reader.getUserInput("Enter product id: ");
        BigDecimal discount = new BigDecimal(reader.getUserInputDouble("Enter discount: "));
        productService.setDiscountById(id, discount);
    }
}