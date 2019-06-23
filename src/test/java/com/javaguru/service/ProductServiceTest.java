package com.javaguru.service;

import com.javaguru.repository.Repository;
import com.javaguru.service.validation.ProductValidationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@Component
public class ProductServiceTest {

    private ProductService victim;
    private Repository repository;
    private ProductValidationService validationService;
    private static Product testProduct;
    private ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
    private ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
    private ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
    private ArgumentCaptor<BigDecimal> bigDecimalCaptor = ArgumentCaptor.forClass(BigDecimal.class);

    @Autowired
    @Before
    public void createFirstProduct() {
        repository = mock(Repository.class);
        validationService = mock(ProductValidationService.class);

        victim = new ProductService(repository, validationService);

        testProduct = new Product();
        testProduct.setName("Apple");
        testProduct.setPrice(new BigDecimal(5));
        testProduct.setCategory(Category.FRUIT);
        testProduct.setId(999L);

        doReturn(Optional.ofNullable(testProduct)).when(repository).getProductById(testProduct.getId());
    }

    @After
    public void cleanAfterEachTest() {
        victim.removeAllProducts();
    }

    @Test
    public void shouldCreateProduct() {
        doReturn(testProduct).when(repository).add(testProduct);

        Long id = victim.createProduct(testProduct);
        verify(validationService).validate(productCaptor.capture());
        assertEquals(productCaptor.getValue(), testProduct);

        verify(repository).add(productCaptor.capture());
        assertEquals(productCaptor.getValue(), testProduct);

        assertEquals(id, testProduct.getId());
    }

    @Test
    public void shouldDeleteById() {
        victim.deleteById(testProduct.getId());
        verify(repository).removeProductById(idCaptor.capture());
        assertEquals(idCaptor.getValue(), testProduct.getId());
    }

    @Test
    public void shouldSetDiscountById() {
        BigDecimal discount = new BigDecimal(40);
        victim.setPriceById(testProduct.getId(), new BigDecimal(100));
        victim.setDiscountById(testProduct.getId(), discount);
        verify(repository).changeDiscount(idCaptor.capture(), bigDecimalCaptor.capture());
        assertEquals(testProduct.getId(), idCaptor.getValue());
        assertEquals(discount, bigDecimalCaptor.getValue());
    }

    @Test
    public void setPriceById() {
        BigDecimal newPrice = new BigDecimal(77);
        victim.setPriceById(testProduct.getId(), newPrice);
        verify(repository).changePrice(idCaptor.capture(), bigDecimalCaptor.capture());
        assertEquals(testProduct.getId(), idCaptor.getValue());
        assertEquals(newPrice, bigDecimalCaptor.getValue());
    }

    @Test
    public void changeProductDescription() {
        String newDescription = "Test description";
        victim.changeProductDescription(testProduct.getId(), newDescription);
        verify(repository).changeDescription(idCaptor.capture(), stringCaptor.capture());
        assertEquals(testProduct.getId(), idCaptor.getValue());
        assertEquals(newDescription, stringCaptor.getValue());
    }

    @Test
    public void changeName() {
        String newName = "TestName";
        victim.changeName(testProduct.getId(), newName);
        verify(repository).changeName(idCaptor.capture(), stringCaptor.capture());
        assertEquals(testProduct.getId(), idCaptor.getValue());
        assertEquals(newName, stringCaptor.getValue());
    }

    @Test
    public void removeAllProducts() {
        victim.removeAllProducts();
        verify(repository).removeAllProducts();
    }
}