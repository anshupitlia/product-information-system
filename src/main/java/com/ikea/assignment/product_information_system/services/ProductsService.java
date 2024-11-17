package com.ikea.assignment.product_information_system.services;

import com.ikea.assignment.product_information_system.exceptions.ProductNotFoundException;
import com.ikea.assignment.product_information_system.model.Product;

import java.util.List;

public interface ProductsService {
    List<Product> getAllProducts();
    Product getProductBy(String sku) throws ProductNotFoundException;
    Product update(Product product);
}
