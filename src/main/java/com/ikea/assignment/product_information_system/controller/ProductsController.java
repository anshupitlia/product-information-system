package com.ikea.assignment.product_information_system.controller;

import com.ikea.assignment.product_information_system.model.Product;
import com.ikea.assignment.product_information_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping(value="/{sku}")
    public Product getProduct(@PathVariable String sku) {
        return productRepository.findById(sku).orElseThrow(RuntimeException::new);
    }
}
