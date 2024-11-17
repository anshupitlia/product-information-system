package com.ikea.assignment.product_information_system.repository;

import com.ikea.assignment.product_information_system.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

@DataCassandraTest
class ProductRepositoryTest {
    @Autowired
    private static ProductsRepository productRepository;

    @BeforeAll
    public static void setup() {
        Product product = new Product();;
        product.setTitle("title");
        product.setSku("sku");
        product.setPrice(BigDecimal.ONE);

        productRepository.save(product);
    }

    @AfterAll
    public static void cleanup() {
        productRepository.deleteById("sku");
    }
    @Test
    @Order(1)
    public void getProductTest(){

        //Action
        Product product = productRepository.findById("sku").get();
        //Verify
        Assertions.assertThat(product.getSku()).isEqualTo("sku");
        Assertions.assertThat(product.getTitle()).isEqualTo("title");
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateProductTest(){

        //Action
        Product employee = productRepository.findById("sku").get();
        Assertions.assertThat(employee.getPrice()).isEqualTo(BigDecimal.ONE);
        employee.setPrice(BigDecimal.TEN);
        Product employeeUpdated =  productRepository.save(employee);

        //Verify
        Assertions.assertThat(employeeUpdated.getPrice()).isEqualTo(BigDecimal.TEN);

    }
}