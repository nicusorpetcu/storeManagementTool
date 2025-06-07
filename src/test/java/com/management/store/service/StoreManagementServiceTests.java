package com.management.store.service;

import com.management.store.entity.ProductEntity;
import com.management.store.exceptions.NegativeNumericValueException;
import com.management.store.exceptions.ProductNotFoundException;
import com.management.store.mapper.ProductEntityMapperImpl;
import com.management.store.model.Product;
import com.management.store.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StoreManagementServiceTests {

    @Mock
    private ProductRepository productRepository;
    @Spy
    private ProductEntityMapperImpl productEntityMapperImpl;
    @InjectMocks
    private StoreManagementService storeManagementService;
    ProductEntity productEntity = new ProductEntity("name", 1, 1);
    Product product = new Product("name", 1, 1);

    @Test
    public void addProductTest() {
        Mockito.when(productRepository.save(productEntity)).thenReturn(new ProductEntity());
        Mockito.when(productEntityMapperImpl.productToEntity(product)).thenReturn(productEntity);

        storeManagementService.addProduct(product);
        Mockito.verify(productRepository).save(productEntity);
    }

    @Test
    public void deleteProductTest_WhenProductNotFound() {
        boolean actual = storeManagementService.deleteProduct(1L);
        assertFalse(actual);
    }

    @Test
    public void deleteProductTest_WhenProductFound() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));
        boolean actual = storeManagementService.deleteProduct(1L);
        assertTrue(actual);
    }

    @Test
    public void updateProductTest_WhenProductNotFound() {
        ProductNotFoundException ex = assertThrows(ProductNotFoundException.class, () -> storeManagementService.updateProduct(new Product(), 1L));
        assertEquals("1", ex.getParam());
    }

    @Test
    public void updateProductTest_WhenProductFound() throws ProductNotFoundException {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));
        boolean actual = storeManagementService.updateProduct(new Product(), 1L);
        assertTrue(actual);
    }

    @Test
    public void updatePriceTest_WhenProductNotFound() {
        ProductNotFoundException ex = assertThrows(ProductNotFoundException.class, () -> storeManagementService.updatePrice(3, 1L));
        assertEquals("1", ex.getParam());
    }

    @Test
    public void updatePriceTest_WhenPriceHasNegativeValue() {
        NegativeNumericValueException ex = assertThrows(NegativeNumericValueException.class, () -> storeManagementService.updatePrice(-3, 1L));
        assertEquals("-3.0", ex.getParam());
    }

    @Test
    public void updatePriceTest_WhenProductFound() throws ProductNotFoundException, NegativeNumericValueException {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));
        boolean actual = storeManagementService.updatePrice(3, 1L);
        assertTrue(actual);
    }

    @Test
    public void updateQuantityTest_WhenProductNotFound() {
        ProductNotFoundException ex = assertThrows(ProductNotFoundException.class, () -> storeManagementService.updateQuantity(10, 1L));
        assertEquals("1", ex.getParam());
    }

    @Test
    public void updateQuantityTest_WhenPriceHasNegativeValue() {
        NegativeNumericValueException ex = assertThrows(NegativeNumericValueException.class, () -> storeManagementService.updateQuantity(-12, 1L));
        assertEquals("-12.0", ex.getParam());
    }

    @Test
    public void updateQuantityTest_WhenProductFound() throws ProductNotFoundException, NegativeNumericValueException {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));
        boolean actual = storeManagementService.updateQuantity(3, 1L);
        assertTrue(actual);
    }

    @Test
    public void getProductTest_WhenProductNotFound() {
        ProductNotFoundException ex = assertThrows(ProductNotFoundException.class, () -> storeManagementService.getProduct(1L));
        assertEquals("1", ex.getParam());
    }

    @Test
    public void getProductTest_WhenProductFound() throws ProductNotFoundException {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(new ProductEntity("name", 1.2, 1)));
        Product actual = storeManagementService.getProduct(1L);
        assertEquals("name", actual.getProductName());
        assertEquals( 1.2, actual.getPrice());
        assertEquals( 1.0, actual.getQuantity());
    }

    @Test
    public void getProductListTest() throws ProductNotFoundException {
        List<ProductEntity> list = new ArrayList<>();
        list.add(new ProductEntity());list.add(new ProductEntity());list.add(new ProductEntity());
        Mockito.when(productRepository.findAll()).thenReturn(list);
        List<Product> actual = storeManagementService.getProductList();
        assertEquals(3, actual.size());
    }
}
