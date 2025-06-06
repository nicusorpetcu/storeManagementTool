package com.management.store.service;

import com.management.store.entity.ProductEntity;
import com.management.store.mapper.ProductEntityMapperImpl;
import com.management.store.model.Product;
import com.management.store.repository.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class StoreManagementService {

    @Autowired
    ProductRepository productRepository;
    @Resource
    ProductEntityMapperImpl productEntityMapper;

    public Product addProduct(Product product) {
        productRepository.save(productEntityMapper.productToEntity(product));
        return product;
    }

    public boolean deleteProduct(Long id) {
        AtomicBoolean flag = new AtomicBoolean(false);
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        productEntity.ifPresent(entity -> {
            productRepository.delete(entity);
            flag.set(true);
        });
        return flag.get();
    }

    public boolean updateProduct(Product product, Long id) {
        AtomicBoolean flag = new AtomicBoolean(false);
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            ProductEntity temp=productEntityMapper.productToEntity(product);
            temp.setId(productEntity.get().getId());
            productRepository.save(temp);
            flag.set(true);
        };
        return flag.get();
    }

    public boolean updatePrice(double price, Long id) {
        AtomicBoolean flag = new AtomicBoolean(false);
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            productEntity.get().setPrice(price);
            productRepository.save(productEntity.get());
            flag.set(true);
        };
        return flag.get();
    }

    public boolean updateQuantity(int quantity, Long id) {
        AtomicBoolean flag = new AtomicBoolean(false);
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            productEntity.get().setQuantity(quantity);
            productRepository.save(productEntity.get());
            flag.set(true);
        };
        return flag.get();
    }

    public Product getProduct(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        Product product = null;
        if (productEntity.isPresent()) {
            product = productEntityMapper.productEntityToProduct(productEntity.get());
        }
        return product;
    }

    public List<Product> getProductList() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityList) {
            productList.add(productEntityMapper.productEntityToProduct(productEntity));

        }
        return productList;
    }
}
