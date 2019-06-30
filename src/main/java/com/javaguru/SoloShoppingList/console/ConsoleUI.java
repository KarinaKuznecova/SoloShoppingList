package com.javaguru.SoloShoppingList.console;

import com.javaguru.SoloShoppingList.console.action.*;

import java.util.List;

public class ConsoleUI {
    private Reader reader = new Reader();
    private final List<MenuItem> menuItems;

    public ConsoleUI(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void go() {
        while (true) {
            try {
                mainMenu();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Error! Please try again.");
            }
        }
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
            menuItems.get(index).action();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Wrong number, try again");
        }
    }
}