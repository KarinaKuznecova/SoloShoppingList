package com.javaguru.service;

import com.javaguru.repository.InMemoryRepository;
import com.javaguru.service.validation.ProductValidationService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class ProductServiceTest {

    private ProductService victim = new ProductService(new InMemoryRepository(), new ProductValidationService());
    private static Product testProduct;

    @Before
    public void createFirstProduct(){
        testProduct = new Product();
        testProduct.setName("Apple");
        testProduct.setPrice(new BigDecimal(5));
        testProduct.setCategory(Category.FRUIT);
        victim.createProduct(testProduct);
    }

    @After
    public void cleanAfterEachTest(){
        victim.removeAllProducts();
    }

    @Test
    public void shouldCreateProduct() {
        Product newProduct = new Product();
        newProduct.setName("Orange");
        newProduct.setPrice(new BigDecimal(5));
        newProduct.setCategory(Category.FRUIT);

        long expectedRepositorySize = victim.getStorageSize()+1;
        victim.createProduct(newProduct);
        long actualRepositorySize = victim.getStorageSize();
        assertEquals(expectedRepositorySize, actualRepositorySize);
    }

    @Test
    public void shouldDeleteById() {
        long expectedRepositorySize = victim.getStorageSize()-1;
        victim.deleteById(0L);
        long actualRepositorySize = victim.getStorageSize();
        assertEquals(expectedRepositorySize, actualRepositorySize);
    }

    @Test
    public void shouldSetDiscountById() {
        BigDecimal discount = new BigDecimal(40);
        victim.setPriceById(0L, new BigDecimal(100));
        victim.setDiscountById(0L, discount);
        BigDecimal actualDiscount = victim.getProductById(0L).getDiscount();
        assertEquals(discount, actualDiscount);
    }

    @Test
    public void shouldSetDiscountPrice(){
        BigDecimal discount = new BigDecimal(40);
        victim.setPriceById(0L, new BigDecimal(100));
        victim.setDiscountById(0L, discount);
        BigDecimal actualDiscountPrice = victim.getProductById(0L).getDiscountPrice();
        BigDecimal expectedPrice = new BigDecimal(60.00).setScale(2, RoundingMode.HALF_EVEN);
        assertEquals(expectedPrice, actualDiscountPrice);
    }

    @Test
    public void setPriceById() {
        BigDecimal newPrice = new BigDecimal(77);
        victim.setPriceById(0L, newPrice);
        assertEquals(newPrice, victim.getProductById(0L).getPrice());

    }

    @Test
    public void changeProductDescription() {
        String newDescription = "Test description";
        victim.changeProductDescription(0L, newDescription);
        assertEquals(newDescription, victim.getProductById(0L).getDescription());
    }

    @Test
    public void changeName() {
        String newName = "TestName";
        victim.changeName(0L, newName);
        assertEquals(newName, victim.getProductById(0L).getName());
    }

    @Test
    public void removeAllProducts() {
        long expectedRepositorySize = 0;
        victim.removeAllProducts();
        long actualRepositorySize = victim.getStorageSize();
        assertEquals(expectedRepositorySize, actualRepositorySize);
    }
}