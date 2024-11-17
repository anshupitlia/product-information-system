package com.ikea.assignment.product_information_system.controller;

import com.ikea.assignment.product_information_system.exceptions.ProductNotFoundException;
import com.ikea.assignment.product_information_system.model.Product;
import com.ikea.assignment.product_information_system.services.ProductsService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping
    public List<Product> getProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping(value="/{sku}")
    public Product getProduct(@PathVariable String sku) {
        try {
            return productsService.getProductBy(sku);
        } catch(ProductNotFoundException exception) {
            System.out.println("This product was not found");
            return new Product();
        }
    }

    @PutMapping("/{sku}")
    public ResponseEntity<Product> updateProduct(@PathVariable String sku, @RequestBody Product product) {
        Product newProduct = productsService.update(product);
        if (!StringUtil.isNullOrEmpty(newProduct.getSku())) {
            return ResponseEntity.ok(newProduct);
        } else {
           return ResponseEntity.badRequest().body(newProduct);
        }
    }
}
