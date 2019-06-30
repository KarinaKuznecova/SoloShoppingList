package com.javaguru.SoloShoppingList.service.validation;

import com.javaguru.SoloShoppingList.service.*;

public interface ProductValidationRule {

    void validate(Product product);

    default void checkNotNull(Product product) {
        if (product == null) {
            throw new ValidationException("Product can't be null");
        }
    }
}
