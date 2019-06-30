package com.javaguru.SoloShoppingList.service.validation;

import com.javaguru.SoloShoppingList.repository.*;
import com.javaguru.SoloShoppingList.service.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ProductValidationServiceTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductRepository repository;
    private ProductValidationService victim;

    @Before
    public void setUp() {
        repository = mock(ProductRepository.class);
        Set<ProductValidationRule> validationRules = new HashSet<>();
        validationRules.add(new ProductNameValidationRule());
        validationRules.add(new ProductPriceValidationRule());
        validationRules.add(new ProductDiscountValidationRule());
        validationRules.add(new ProductUniquenessValidationRule(repository));
        victim = new ProductValidationService(validationRules);
    }

    @Test
    public void shouldValidate() {
        Product product = new Product();
        product.setName("Apple");
        product.setPrice(new BigDecimal(12));
        product.setCategory(Category.FRUIT);
        victim.validate(product);
    }

    @Test
    public void shouldNotValidateByShortName() {
        Product product = new Product();
        product.setName("A");
        product.setPrice(new BigDecimal(12));
        product.setCategory(Category.FRUIT);

        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Name length should be more than 3 symbols and less than 25");

        victim.validate(product);
    }

    @Test
    public void shouldNotValidateByLongName() {
        Product product = new Product();
        product.setName("Abcdefghijklmnopqrstuvwxyz0123456789");
        product.setPrice(new BigDecimal(12));
        product.setCategory(Category.FRUIT);

        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Name length should be more than 3 symbols and less than 25");

        victim.validate(product);
    }

    @Test
    public void shouldNotValidateByNegativePrice() {
        Product product = new Product();
        product.setName("Apple");
        product.setPrice(new BigDecimal(-12));
        product.setCategory(Category.FRUIT);

        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Price must be more than 0");

        victim.validate(product);
    }

    @Test
    public void shouldNotValidateByBigDiscount() {
        Product product = new Product();
        product.setName("Apple");
        product.setPrice(new BigDecimal(30));
        product.setCategory(Category.FRUIT);

        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Discount must be more than 0% and less than 80%");

        victim.validateDiscount(new BigDecimal(100), product);
    }

    @Test
    public void shouldNotValidateByNegativeDiscount() {
        Product product = new Product();
        product.setName("Apple");
        product.setPrice(new BigDecimal(30));
        product.setCategory(Category.FRUIT);

        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Discount must be more than 0% and less than 80%");

        victim.validateDiscount(new BigDecimal(-10), product);
    }

    @Test
    public void shouldNotValidateNotEligibleForDiscount() {
        Product product = new Product();
        product.setName("Apple");
        product.setPrice(new BigDecimal(10));
        product.setCategory(Category.FRUIT);

        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Can't set discount to product cheaper than 20");

        victim.validateDiscount(new BigDecimal(50), product);
    }

    @Test
    public void shouldNotValidateByUniqueness() {
        Product product = new Product();
        product.setName("Apple");
        product.setPrice(new BigDecimal(10));
        product.setCategory(Category.FRUIT);
        product.setDiscount(new BigDecimal(50));

        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Products must be unique");

        doReturn(true).when(repository).containsProduct(product);
        victim.validate(product);
    }
}