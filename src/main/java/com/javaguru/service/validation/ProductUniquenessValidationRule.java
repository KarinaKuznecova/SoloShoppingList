package com.javaguru.service.validation;

import com.javaguru.repository.InMemoryRepository;
import com.javaguru.repository.Repository;
import com.javaguru.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUniquenessValidationRule implements ProductValidationRule {

    Repository repository;

    @Autowired
    public ProductUniquenessValidationRule(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (repository.containsProduct(product)){
            throw new ValidationException("Products must be unique");
        }
    }
}
