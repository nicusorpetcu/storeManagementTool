package com.management.store.service;

import com.management.store.entity.ProductEntity;
import com.management.store.mapper.ProductEntityMapper;
import com.management.store.mapper.ProductEntityMapperImpl;
import com.management.store.model.Product;
import com.management.store.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class StoreManagementServiceTests {

    @Mock
    private ProductRepository productRepository;
    @Spy
    private ProductEntityMapperImpl productEntityMapperImpl;
    @InjectMocks
    private StoreManagementService storeManagementService;
    ProductEntity productEntity=new ProductEntity("name",1,1);
    Product product=new Product("name",1,1);

    @Test
    public void addProductTest(){
        Mockito.when(productRepository.save(productEntity)).thenReturn(new ProductEntity());
        Mockito.when(productEntityMapperImpl.productToEntity(product)).thenReturn(productEntity);

        storeManagementService.addProduct(product);
        Mockito.verify(productRepository).save(productEntity);
    }

    @Test
    public void deleteProductTest_WhenProductNotFound(){
        boolean actual = storeManagementService.deleteProduct(1L);
        assertFalse(actual);
    }

    @Test
    public void deleteProductTest_WhenProductFound(){
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));
        boolean actual = storeManagementService.deleteProduct(1L);
        assertTrue(actual);
    }
}
