package com.javaguru.console;

import com.javaguru.service.ProductService;

class FindByIdMenu implements MenuItem {
    private Reader reader = new Reader();

    @Override
    public void action(ProductService productService) {
        long id = reader.getUserInput("Enter product id: ");
        System.out.println(productService.getProductById(id));
    }
}
