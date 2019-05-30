package com.javaguru.console;

import com.javaguru.service.ProductService;

import java.util.List;

class CreateNewShoppingCartMenu implements MenuItem {

    private Reader reader = new Reader();
    private List<ShoppingCart> shoppingCarts;

    CreateNewShoppingCartMenu(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public void action(ProductService productService) {
        String name = reader.getUserInputLine("Enter name for new shopping cart");
        String description = reader.getUserInputLine("Enter description for new shopping cart");
        ShoppingCart cart = new ShoppingCart(name, description);
        shoppingCarts.add(cart);
        System.out.println("Created shopping cart: ");
        System.out.println(cart);
    }
}