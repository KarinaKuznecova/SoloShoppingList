package com.javaguru.service.validation;

import com.javaguru.service.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductPriceValidationRule implements ProductValidationRule{

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Price must be more than 0");
        }
    }
}
