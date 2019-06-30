package com.javaguru.SoloShoppingList.console.action;

import com.javaguru.SoloShoppingList.repository.*;
import com.javaguru.SoloShoppingList.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCart {

    private String name;
    private String description;

    @Autowired
    private ProductRepository repository;

    private List<Long> products = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    void addProduct(Product product) {
        products.add(product.getId());
    }

    public void removeProduct(Product product) {
        products.remove(product.getId());
    }

    void printAllProducts() {
        for (Long i : products) {
            System.out.println(repository.getProductById(i));
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
