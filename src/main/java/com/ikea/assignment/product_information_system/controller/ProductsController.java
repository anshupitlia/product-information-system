package com.ikea.assignment.product_information_system.controller;

import com.ikea.assignment.product_information_system.model.Country;
import com.ikea.assignment.product_information_system.model.Product;
import com.ikea.assignment.product_information_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    private Product faltuSetup() {
        Product product = new Product();
        product.setSku("1");
        product.setTitle("Title");
        product.setPrice(BigDecimal.TEN);
        product.setCountry(Country.EU);
        product.setDiscountPercentage(2.0);
        product.setImageUrl("https://assets.dummyjson.com/public/qr-code.png");
        return product;
    }
    @GetMapping
    public List<Product> getProducts() {
        Product product = faltuSetup();
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
        //return productRepository.findAll();
    }

    @GetMapping(value="/{sku}")
    public Product getProduct(@PathVariable String sku) {
        return faltuSetup();
        //return productRepository.findById(sku).orElseThrow(RuntimeException::new);
    }

    @PutMapping("/{sku}")
    public ResponseEntity updateProduct(@PathVariable String sku, @RequestBody Product product) {
        Product currentProduct = productRepository.findById(sku).orElseThrow(RuntimeException::new);
        currentProduct.setCountry(product.getCountry());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setDiscountPercentage(product.getDiscountPercentage());
        System.out.println("This product will be saved" + currentProduct.toString());
        Product newProduct = productRepository.save(currentProduct);

        return ResponseEntity.ok(newProduct);
    }
}
