package com.ikea.assignment.product_information_system.services;

import com.ikea.assignment.product_information_system.exceptions.ProductNotFoundException;
import com.ikea.assignment.product_information_system.model.Country;
import com.ikea.assignment.product_information_system.model.Product;
import com.ikea.assignment.product_information_system.producers.ProductPriceUpdateProducer;
import com.ikea.assignment.product_information_system.repository.ProductsRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductsServiceImplTest {

    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private ProductPriceUpdateProducer productPriceUpdateProducer;

    @InjectMocks
    private ProductsServiceImpl productsService;

    private Product product;


    @BeforeEach
    public void setup(){
        product = new Product();
        product.setSku("sku");
        product.setTitle("title");
        product.setPrice(BigDecimal.TEN);
        product.setCountry(Country.EU);
        product.setDiscountPercentage(2.0);

    }

    @Test
    @Order(1)
    public void getProductBySku() throws ProductNotFoundException {
        // precondition
        given(productsRepository.findById("sku")).willReturn(Optional.of(product));

        // action
        Product existingProduct = productsService.getProductBy(product.getSku());

        // verify
        System.out.println(existingProduct);
        assertThat(existingProduct).isNotNull();
    }


    @Test
    @Order(2)
    public void getAllProducts(){
        Product newProduct = new Product();
        newProduct.setSku("sku1");
        newProduct.setTitle("title1");
        newProduct.setPrice(BigDecimal.ONE);
        newProduct.setCountry(Country.IN);
        newProduct.setDiscountPercentage(2.0);

        // precondition
        given(productsRepository.findAll()).willReturn(List.of(product,newProduct));

        // action
        List<Product> allProducts = productsService.getAllProducts();

        // verify
        System.out.println(allProducts);
        assertThat(allProducts).isNotNull();
        assertThat(allProducts.size()).isGreaterThan(1);
    }

    @Test
    @Order(3)
    public void updateProducts(){

        // precondition
        given(productsRepository.findById(product.getSku())).willReturn(Optional.of(product));

        Product updateProduct = new Product();
        updateProduct.setSku("sku");
        updateProduct.setTitle("title");
        updateProduct.setPrice(BigDecimal.ONE);
        updateProduct.setCountry(Country.EU);
        updateProduct.setDiscountPercentage(2.0);

        given(productsRepository.save(any())).willReturn(updateProduct);

        // action
        Product updatedProduct = productsService.update(updateProduct);

        // verify
        System.out.println(updatedProduct);
        assertThat(updatedProduct.getPrice()).isEqualTo(BigDecimal.ONE);
        verify(productPriceUpdateProducer).send(any(), any());
    }
}