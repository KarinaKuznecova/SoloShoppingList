package com.javaguru.console;

import com.javaguru.service.ProductService;

import java.util.List;

class AddToCartMenu implements MenuItem {
    Reader reader = new Reader();
    private List<ShoppingCart> shoppingCarts;

    AddToCartMenu(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public void action(ProductService productService) {
        for (int i = 0; i < shoppingCarts.size(); i++) {
            System.out.println(i + ". " + shoppingCarts.get(i));
        }
        int chosenList = reader.getUserInput("Enter number to pick a cart");
        long productId = reader.getUserInput("Enter id of product to add to this cart");

        shoppingCarts.get(chosenList).addProduct(productService.getProductById(productId));
        System.out.println("Product with id " + productId + " added");
    }
}