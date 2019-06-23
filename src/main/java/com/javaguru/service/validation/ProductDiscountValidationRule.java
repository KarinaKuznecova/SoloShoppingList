package com.javaguru.service.validation;

import com.javaguru.service.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDiscountValidationRule implements ProductValidationRule {

    private BigDecimal maxDiscount = new BigDecimal(80);

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getDiscount() != null) {
            if (product.getDiscount().compareTo(BigDecimal.ZERO) < 0 || product.getDiscount().compareTo(maxDiscount) > 0) {
                throw new ValidationException("Discount must be more than 0% and less than 80%");
            }
        }
    }
}