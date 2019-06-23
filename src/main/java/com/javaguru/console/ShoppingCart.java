package com.javaguru.console;

import com.javaguru.repository.Repository;
import com.javaguru.service.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private String name;
    private String description;

    @Autowired
    private Repository repository;

    private List<Long> products = new ArrayList<>();

    ShoppingCart(String name, String description) {
        this.name = name;
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
