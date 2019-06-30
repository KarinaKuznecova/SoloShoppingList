package com.javaguru.SoloShoppingList.service.validation;

import com.javaguru.SoloShoppingList.repository.*;
import com.javaguru.SoloShoppingList.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUniquenessValidationRule implements ProductValidationRule {

    ProductRepository repository;

    @Autowired
    public ProductUniquenessValidationRule(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (repository.containsProduct(product)) {
            throw new ValidationException("Products must be unique");
        }
    }
}
