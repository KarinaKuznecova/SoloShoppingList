package com.javaguru.SoloShoppingList.service.validation;

import com.javaguru.SoloShoppingList.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class ProductValidationService {

    private Set<ProductValidationRule> validationRules;

    @Autowired
    public ProductValidationService(Set<ProductValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(Product product) {
        validationRules.forEach(s -> s.validate(product));
    }

    public void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Price must be more than 0");
        }
    }

    public void validateName(String name) {
        if (name.length() < 3 || name.length() > 30) {
            throw new ValidationException("Name length should be more than 3 symbols and less than 30");
        }
    }

    public void validateDiscount(BigDecimal discount, Product product) {
        BigDecimal maxDiscount = new BigDecimal(80);
        eligibleForDiscount(product);
        if (discount.compareTo(BigDecimal.ZERO) < 0 || discount.compareTo(maxDiscount) > 0) {
            throw new ValidationException("Discount must be more than 0% and less than 80%");
        }
    }

    private void eligibleForDiscount(Product product) {
        BigDecimal minEligiblePrice = new BigDecimal(20);
        if (product.getPrice().compareTo(minEligiblePrice) <= 0) {
            throw new ValidationException("Can't set discount to product cheaper than 20");
        }
    }
}