package com.javaguru.SoloShoppingList.console.action;

import com.javaguru.SoloShoppingList.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreateNewMenu implements MenuItem {
    private Reader reader = new Reader();
    private ProductService productService;

    @Autowired
    public CreateNewMenu(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void action() {
        CategoryChooser categoryChooser = new CategoryChooser();
        String name = reader.getUserInputLine("Enter product name: ");
        BigDecimal price = BigDecimal.valueOf(reader.getUserInputDouble("Enter product price: "));
        String description = reader.getUserInputLine("Enter description (optional): ");
        Category category = categoryChooser.getCategory();
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        if (!description.isEmpty()) {
            product.setDescription(description);
        }
        System.out.println("Product created with id: " + productService.createProduct(product));
    }
}