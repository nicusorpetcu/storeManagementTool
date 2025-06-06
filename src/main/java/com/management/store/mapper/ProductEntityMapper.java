package com.management.store.mapper;

import com.management.store.entity.ProductEntity;
import com.management.store.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductEntityMapper {
    ProductEntity productToEntity(Product product);

    Product productEntityToProduct(ProductEntity productEntity);
}

