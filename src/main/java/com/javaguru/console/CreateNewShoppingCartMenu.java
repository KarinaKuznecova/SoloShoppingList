package com.javaguru.console;

import com.javaguru.repository.Repository;
import com.javaguru.service.ProductService;

import java.util.List;

class CreateNewShoppingCartMenu implements MenuItem {

    private Reader reader = new Reader();
    private List<ShoppingCart> shoppingCarts;
    private Repository repository;

    CreateNewShoppingCartMenu(List<ShoppingCart> shoppingCarts, Repository repository) {
        this.shoppingCarts = shoppingCarts;
        this.repository = repository;
    }

    @Override
    public void action(ProductService productService) {
        String name = reader.getUserInputLine("Enter name for new shopping cart");
        String description = reader.getUserInputLine("Enter description for new shopping cart");
        ShoppingCart cart = new ShoppingCart(name, description, repository);
        shoppingCarts.add(cart);
        System.out.println("Created shopping cart: ");
        System.out.println(cart);
    }
}