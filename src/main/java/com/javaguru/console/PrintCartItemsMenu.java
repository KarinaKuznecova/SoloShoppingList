package com.javaguru.console;

import com.javaguru.service.ProductService;

import java.util.List;

class PrintCartItemsMenu implements MenuItem {

    private Reader reader = new Reader();
    private List<ShoppingCart> shoppingCarts;

    PrintCartItemsMenu(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public void action(ProductService productService) {
        for (int i = 0; i < shoppingCarts.size(); i++) {
            System.out.println(i + ". " + shoppingCarts.get(i));
        }
        int chosenList = reader.getUserInput("Enter number to pick a cart");

        shoppingCarts.get(chosenList).printAllProducts();
    }
}