package com.javaguru.repository;

import com.javaguru.service.Product;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository {

    private Long productIdSequence = 0L;
    private Map<Long, Product> productRepository = new HashMap<>();

    public Product add(Product product) {
        product.setId(productIdSequence);
        productRepository.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }

    public Product getProductById(Long id) {
        return productRepository.get(id);
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
}