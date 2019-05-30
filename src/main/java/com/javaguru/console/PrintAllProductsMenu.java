package com.javaguru.console;

import com.javaguru.service.ProductService;

class PrintAllProductsMenu implements MenuItem {

    @Override
    public void action(ProductService productService) {
        productService.printAll();
    }
}
