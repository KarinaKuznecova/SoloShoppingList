package com.javaguru.SoloShoppingList.console.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintCartItemsMenu implements MenuItem {

    private Reader reader = new Reader();
    private List<ShoppingCart> shoppingCarts;

    @Autowired
    PrintCartItemsMenu(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public void action() {
        for (int i = 0; i < shoppingCarts.size(); i++) {
            System.out.println(i + ". " + shoppingCarts.get(i));
        }
        int chosenList = reader.getUserInput("Enter number to pick a cart");

        shoppingCarts.get(chosenList).printAllProducts();
    }
}