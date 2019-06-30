package com.javaguru.SoloShoppingList.console.action;

import com.javaguru.SoloShoppingList.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddToCartMenu implements MenuItem {
    Reader reader = new Reader();
    private List<ShoppingCart> shoppingCarts;
    private ProductService productService;

    @Autowired
    AddToCartMenu(List<ShoppingCart> shoppingCarts, ProductService productService) {
        this.shoppingCarts = shoppingCarts;
        this.productService = productService;
    }

    @Override
    public void action() {
        for (int i = 0; i < shoppingCarts.size(); i++) {
            System.out.println(i + ". " + shoppingCarts.get(i));
        }
        int chosenList = reader.getUserInput("Enter number to pick a cart");
        long productId = reader.getUserInput("Enter id of product to add to this cart");

        shoppingCarts.get(chosenList).addProduct(productService.getProductById(productId));
        System.out.println("Product with id " + productId + " added");
    }
}