package com.javaguru.console;

import com.javaguru.service.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private String name;
    private String description;

    private List<Product> products = new ArrayList<>();

    ShoppingCart(String name, String description) {
        this.name = name;
        this.description = description;
    }

    void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    void printAllProducts() {
        for (Product i : products) {
            System.out.println(i);
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
