package com.javaguru.repository;

import com.javaguru.service.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

public class InMemoryRepositoryTest {

    private Repository victim;
    private Product testProduct;

    @Before
    public void createFirstProduct() {
        victim = new InMemoryRepository();
        testProduct = spy(Product.class);
        testProduct.setId(999L);
    }

    @Test
    public void shouldAddNewProduct() {
        long startingRepositorySize = victim.getStorageSize();
        victim.add(testProduct);
        assertEquals(++startingRepositorySize, victim.getStorageSize());
    }

    @Test
    public void shouldGetProductById() {
        victim.add(testProduct);
        Long id = testProduct.getId();
        Product receivedProduct = victim.getProductById(id).orElseThrow(() ->
                new IllegalArgumentException("Product with id " + id + " not found"));
        assertEquals(testProduct, receivedProduct);
    }

    @Test
    public void shouldRemoveById() {
        victim.add(testProduct);
        long expectedRepositorySize = victim.getStorageSize() - 1;
        victim.removeProductById(testProduct.getId());
        assertEquals(expectedRepositorySize, victim.getStorageSize());
    }

    @Test
    public void shouldRemoveAll() {
        victim.add(testProduct);
        victim.add(testProduct);
        long expectedRepositorySize = 0;
        victim.removeAllProducts();
        assertEquals(expectedRepositorySize, victim.getStorageSize());
    }

    @Test
    public void shouldSayIfContains() {
        victim.add(testProduct);
        assertTrue(victim.containsProduct(testProduct));
    }
}