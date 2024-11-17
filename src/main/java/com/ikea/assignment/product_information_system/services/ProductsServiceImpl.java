package com.ikea.assignment.product_information_system.services;

import com.ikea.assignment.product_information_system.exceptions.ProductNotFoundException;
import com.ikea.assignment.product_information_system.model.Product;
import com.ikea.assignment.product_information_system.producers.ProductCountryUpdateProducer;
import com.ikea.assignment.product_information_system.producers.ProductDiscountUpdateProducer;
import com.ikea.assignment.product_information_system.producers.ProductPriceUpdateProducer;
import com.ikea.assignment.product_information_system.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productRepository;
    @Autowired
    private ProductPriceUpdateProducer productPriceUpdateProducer;
    @Autowired
    private ProductCountryUpdateProducer productCountryUpdateProducer;
    @Autowired
    private ProductDiscountUpdateProducer productDiscountUpdateProducer;

    public ProductsServiceImpl(ProductsRepository productRepository, ProductPriceUpdateProducer productPriceUpdateProducer, ProductCountryUpdateProducer productCountryUpdateProducer, ProductDiscountUpdateProducer productDiscountUpdateProducer) {
        this.productRepository = productRepository;
        this.productPriceUpdateProducer = productPriceUpdateProducer;
        this.productCountryUpdateProducer = productCountryUpdateProducer;
        this.productDiscountUpdateProducer = productDiscountUpdateProducer;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductBy(String sku) throws ProductNotFoundException {
        return productRepository.findById(sku).orElseThrow(ProductNotFoundException::new);
    }

    //this method needs serious refactoring
    @Override
    public Product update(Product product) {
            Optional<Product> optionalCurrentProduct = productRepository.findById(product.getSku());
            if (optionalCurrentProduct.isEmpty()) {
                return new Product();
            }
            Product currentProduct = optionalCurrentProduct.get();
            Product newProduct = currentProduct;
            if (!Objects.equals(currentProduct.getPrice(), product.getPrice())) {
                currentProduct.setPrice(product.getPrice());
                //this has to be done using outbox pattern
                newProduct = productRepository.save(currentProduct);
                productPriceUpdateProducer.send(currentProduct, newProduct);
            }
            if (!Objects.equals(currentProduct.getCountry(), product.getCountry())) {
                currentProduct.setCountry(product.getCountry());
                newProduct = productRepository.save(currentProduct);
                productCountryUpdateProducer.send(currentProduct, newProduct);
            }
            if (!Objects.equals(currentProduct.getDiscountPercentage(), product.getDiscountPercentage())) {
                currentProduct.setDiscountPercentage(product.getDiscountPercentage());
                newProduct = productRepository.save(currentProduct);
                productDiscountUpdateProducer.send(currentProduct, newProduct);
            }
            return newProduct;
    }
}
