package com.javaguru.service;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {

    private String name;
    private String description;

    private List<Product> products = new ArrayList<>();

    public ShoppingList(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public void printAllProducts(){
        for (Product i : products){
            System.out.println(i);
        }
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
