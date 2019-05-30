package com.javaguru.service.validation;

import com.javaguru.service.Product;

public interface ProductValidationRule {

    void validate(Product product);

    default void checkNotNull(Product product) {
        if (product == null) {
            throw new ValidationException("Product can't be null");
        }
    }
}
