package com.javaguru.SoloShoppingList.repository;

import com.javaguru.SoloShoppingList.service.*;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductRepository {

    Product add(Product product);

    Optional<Product> getProductById(Long id);

    void removeProductById(Long id);

    void removeAllProducts();

    void printAll();

    boolean containsProduct(Product product);

    long getStorageSize();

    void changeName(Long id, String newName);

    void changePrice(Long id, BigDecimal newPrice);

    void changeDiscount(Long id, BigDecimal discount);

    void changeDescription(Long id, String newDescription);
}
