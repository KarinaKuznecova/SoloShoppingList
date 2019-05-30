package com.javaguru.console;

import com.javaguru.service.ProductService;

class DeleteByIdMenu implements MenuItem {
    private Reader reader = new Reader();

    @Override
    public void action(ProductService productService) {
        long id = reader.getUserInput("Enter product id: ");
        productService.deleteById(id);
    }
}