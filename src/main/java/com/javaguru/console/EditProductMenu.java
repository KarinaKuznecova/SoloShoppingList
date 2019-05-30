package com.javaguru.console;

import com.javaguru.service.ProductService;

import java.math.BigDecimal;

class EditProductMenu implements MenuItem {
    private Reader reader = new Reader();

    @Override
    public void action(ProductService productService) {
        long id = reader.getUserInput("Enter product Id");
        int pickedAction = pickEditAction();
        switch (pickedAction) {
            case 1:
                String newName = reader.getUserInputLine("Enter new name");
                productService.changeName(id, newName);
                break;
            case 2:
                BigDecimal newPrice = BigDecimal.valueOf(reader.getUserInputDouble("Enter new price"));
                productService.setPriceById(id, newPrice);
                break;
            case 3:
                BigDecimal newDiscount = BigDecimal.valueOf(reader.getUserInputDouble("Enter new discount"));
                productService.setDiscountById(id, newDiscount);
                break;
            case 4:
                String newDescription = reader.getUserInputLine("Enter new description");
                productService.changeProductDescription(id, newDescription);
                break;
            default:
                System.out.println("Wrong number");
                action(productService);
        }
        System.out.println("Information is updated");
    }

    private int pickEditAction() {
        System.out.println("Please, choose what to do:");
        System.out.println();
        System.out.println("1. Change name");
        System.out.println("2. Change price");
        System.out.println("3. Set Discount");
        System.out.println("4. Change description");
        System.out.println();
        return reader.getUserInput("Enter a number: ");
    }
}