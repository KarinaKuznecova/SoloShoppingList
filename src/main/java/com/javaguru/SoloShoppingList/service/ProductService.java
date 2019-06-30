package com.javaguru.SoloShoppingList.service;

import com.javaguru.SoloShoppingList.service.validation.*;
import com.javaguru.SoloShoppingList.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class ProductService {

    private ProductRepository repository;
    private ProductValidationService validationService;

    @Autowired
    public ProductService(ProductRepository repository, ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    @Transactional
    public Long createProduct(Product product) {
        validationService.validate(product);
        Product createdProduct = repository.add(product);
        return createdProduct.getId();
    }

    public Product getProductById(Long id) {
        return repository.getProductById(id).orElseThrow(() ->
                new IllegalArgumentException("Product with id " + id + " not found"));
    }

    public void printAll() {
        repository.printAll();
    }

    public void deleteById(Long id) {
        repository.removeProductById(id);
    }

    public void setDiscountById(Long id, BigDecimal discount) {
        validationService.validateDiscount(discount, getProductById(id));
        repository.changeDiscount(id, discount);
    }

    public void setPriceById(Long id, BigDecimal price) {
        validationService.validatePrice(price);
        repository.changePrice(id, price);
    }

    public void changeProductDescription(long id, String description) {
        repository.changeDescription(id, description);
    }

    public void changeName(long id, String name) {
        validationService.validateName(name);
        repository.changeName(id, name);
    }

    public void removeAllProducts() {
        repository.removeAllProducts();
    }

    public long getStorageSize() {
        return repository.getStorageSize();
    }
}