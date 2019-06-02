package com.javaguru.console;

import com.javaguru.repository.InMemoryRepository;
import com.javaguru.service.ProductService;
import com.javaguru.service.validation.ProductValidationService;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUI {
    private Reader reader = new Reader();
    private ProductService productService = new ProductService(new InMemoryRepository(), new ProductValidationService());
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private List<MenuItem> menuItems = new ArrayList<>();

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
        menuItems.add(6, new CreateNewShoppingCartMenu(shoppingCarts));
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