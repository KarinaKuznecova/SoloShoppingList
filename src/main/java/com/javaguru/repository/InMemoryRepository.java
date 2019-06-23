package com.javaguru.repository;

import com.javaguru.service.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Profile("InMemoryDB")
public class InMemoryRepository implements Repository {

    private Long productIdSequence = 0L;
    private Map<Long, Product> productRepository = new HashMap<>();

    public Product add(Product product) {
        product.setId(productIdSequence);
        productRepository.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productRepository.get(id));
    }

    public void removeProductById(Long id) {
        productRepository.remove(id);
    }

    public void removeAllProducts() {
        productRepository.clear();
        productIdSequence = 0L;
    }

    public void printAll() {
        for (Product i : productRepository.values()) {
            System.out.println(i);
        }
    }

    public boolean containsProduct(Product product) {
        return productRepository.containsValue(product);
    }

    public long getStorageSize() {
        return productRepository.size();
    }

    public void changeName(Long id, String newName){
        productRepository.get(id).setName(newName);
    }

    public void changePrice(Long id, BigDecimal newPrice) {
        productRepository.get(id).setPrice(newPrice);
    }

    public void changeDiscount(Long id, BigDecimal discount) {
        productRepository.get(id).setDiscount(discount);
    }

    public void changeDescription(Long id, String newDescription) {
        productRepository.get(id).setDescription(newDescription);
    }
}