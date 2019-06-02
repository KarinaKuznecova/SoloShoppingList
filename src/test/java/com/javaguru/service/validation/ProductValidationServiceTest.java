package com.javaguru.service.validation;

import com.javaguru.repository.InMemoryRepository;
import com.javaguru.service.Category;
import com.javaguru.service.Product;
import com.javaguru.service.ProductService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductValidationServiceTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductValidationService victim = new ProductValidationService();

    private Product product;

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
        ProductService productService = new ProductService(new InMemoryRepository(), victim);
        productService.createProduct(product);

        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Products must be unique");

        productService.createProduct(product);
    }
}