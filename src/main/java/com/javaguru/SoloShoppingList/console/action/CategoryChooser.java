package com.javaguru.SoloShoppingList.console.action;

import com.javaguru.SoloShoppingList.service.*;

public class CategoryChooser {
    private Reader reader = new Reader();

    Category getCategory() {
        System.out.println("Enter product category:");
        System.out.println("1. " + Category.FRUIT);
        System.out.println("2. " + Category.VEGETABLE);
        System.out.println("3. " + Category.MILK);
        System.out.println("4. " + Category.MEAT);
        System.out.println("5. " + Category.FISH);
        System.out.println("6. " + Category.ALCOHOL);
        int enteredCategory = reader.getUserInput("Enter a number: ");

        switch (enteredCategory) {
            case 1:
                return Category.FRUIT;
            case 2:
                return Category.VEGETABLE;
            case 3:
                return Category.MILK;
            case 4:
                return Category.MEAT;
            case 5:
                return Category.FISH;
            case 6:
                return Category.ALCOHOL;
            default:
                System.out.println("Wrong number");
                getCategory();
        }
        return null;
    }
}
