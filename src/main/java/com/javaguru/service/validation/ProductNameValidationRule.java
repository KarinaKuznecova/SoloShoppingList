package com.javaguru.service.validation;


import com.javaguru.service.Product;

class ProductNameValidationRule implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getName().length() < 3 || product.getName().length() > 25) {
            throw new ValidationException("Name length should be more than 3 symbols and less than 25");
        }
    }
}