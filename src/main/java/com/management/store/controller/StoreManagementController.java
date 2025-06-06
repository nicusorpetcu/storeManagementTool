package com.management.store.controller;

import com.management.store.exceptions.ProductNotFoundException;
import com.management.store.model.Product;
import com.management.store.service.StoreManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreManagementController {

    @Autowired
    private StoreManagementService storeManagementService;

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return storeManagementService.addProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        return storeManagementService.deleteProduct(id);
    }

    @PutMapping("/products/{id}")
    public boolean replaceProduct(@RequestBody Product product, @PathVariable Long id) throws ProductNotFoundException {
        return storeManagementService.updateProduct(product, id);
    }

    @PutMapping("/products/price/{id}")
    public boolean updatePrice(@RequestBody Double price, @PathVariable Long id) throws ProductNotFoundException {
        return storeManagementService.updatePrice(price, id);
    }

    @PutMapping("/products/quantity/{id}")
    public boolean updateQuantity(@RequestBody Integer quantity, @PathVariable Long id) throws ProductNotFoundException {
        return storeManagementService.updateQuantity(quantity, id);
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) throws ProductNotFoundException {
        return storeManagementService.getProduct(id);
    }

    @GetMapping("/products")
    public List<Product> getProductList(){
        return storeManagementService.getProductList();
    }

}
