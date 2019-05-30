package com.javaguru.service;

import com.javaguru.repository.InMemoryRepository;
import com.javaguru.service.validation.*;

import java.math.BigDecimal;

public class ProductService {

    private InMemoryRepository repository = new InMemoryRepository();
    private ProductValidationService validationService = new ProductValidationService();

    public Long createProduct(Product product) {
        validationService.validate(product);
        validationService.validateUniqueness(product, repository);
        Product createdProduct = repository.add(product);
        return createdProduct.getId();
    }

    public Product getProductById(Long id) {
        return repository.getProductById(id);
    }

    public void printAll() {
        repository.printAll();
    }

    public void deleteById(Long id) {
        repository.removeProductById(id);
    }

    public void setDiscountById(Long id, BigDecimal discount) {
        validationService.validateDiscount(discount, repository.getProductById(id));
        repository.getProductById(id).setDiscount(discount);
    }

    public void setPriceById(Long id, BigDecimal price) {
        validationService.validatePrice(price);
        repository.getProductById(id).setPrice(price);
    }

    public void changeProductDescription(long id, String description) {
        repository.getProductById(id).setDescription(description);
    }

    public void changeName(long id, String name) {
        validationService.validateName(name);
        repository.getProductById(id).setName(name);
    }

    public void removeAllProducts() {
        repository.removeAllProducts();
    }
}