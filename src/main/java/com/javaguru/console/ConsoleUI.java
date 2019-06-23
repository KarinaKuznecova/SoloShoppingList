package com.javaguru.console;

import com.javaguru.repository.Repository;
import com.javaguru.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleUI {
    private Reader reader = new Reader();
    private final ProductService productService;
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private List<MenuItem> menuItems = new ArrayList<>();
    private Repository repository;

    @Autowired
    public ConsoleUI(ProductService productService, Repository repository) {
        this.productService = productService;
        this.repository = repository;
    }

    public void go() {
        setUp();
        while (true) {
            try {
                mainMenu();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Error! Please try again.");
            }
        }
    }

    private void setUp() {
        menuItems.add(0, new CreateNewMenu());
        menuItems.add(1, new FindByIdMenu());
        menuItems.add(2, new AddDiscountByIdMenu());
        menuItems.add(3, new EditProductMenu());
        menuItems.add(4, new DeleteByIdMenu());
        menuItems.add(5, new PrintAllProductsMenu());
        menuItems.add(6, new CreateNewShoppingCartMenu(shoppingCarts, repository));
        menuItems.add(7, new AddToCartMenu(shoppingCarts));
        menuItems.add(8, new PrintCartItemsMenu(shoppingCarts));
    }

    private void mainMenu() {
        System.out.println("1. Create product");
        System.out.println("2. Find product by id");
        System.out.println("3. Add discount by id");
        System.out.println("4. Edit product by id");
        System.out.println("5. Delete product by id");
        System.out.println("6. Print all products");
        System.out.println("7. Create new shopping cart");
        System.out.println("8. Add product to shopping cart");
        System.out.println("9. Print products from shopping cart");

        int pickedNumber = reader.getUserInput("Enter a number: ");
        menuSelected(pickedNumber);
    }

    private void menuSelected(int userPickedNumber) {
        int index = userPickedNumber - 1;
        try {
            menuItems.get(index).action(productService);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Wrong number, try again");
        }
    }
}