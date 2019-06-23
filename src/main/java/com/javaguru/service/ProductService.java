package com.javaguru.service;

import com.javaguru.repository.Repository;
import com.javaguru.service.validation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductService {

    private Repository repository;
    private ProductValidationService validationService;

    @Autowired
    public ProductService(Repository repository, ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

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