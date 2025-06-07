package com.management.store.mapper;

import com.management.store.entity.ProductEntity;
import com.management.store.model.Product;
import org.springframework.stereotype.Component;

@Component
public final class ProductEntityMapperImpl implements ProductEntityMapper {
    @Override
    public ProductEntity productToEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getProductName());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQuantity());
        return productEntity;
    }

    @Override
    public Product productEntityToProduct(ProductEntity productEntity) {
        Product product = new Product();
        product.setProductName(productEntity.getName());
        product.setPrice(productEntity.getPrice());
        product.setQuantity(productEntity.getQuantity());
        return product;
    }
}
