package com.javaguru.console;

import com.javaguru.repository.Repository;
import com.javaguru.service.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private String name;
    private String description;
    private Repository repository;

    private List<Long> products = new ArrayList<>();

    ShoppingCart(String name, String description, Repository repository) {
        this.name = name;
        this.description = description;
        this.repository = repository;
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
