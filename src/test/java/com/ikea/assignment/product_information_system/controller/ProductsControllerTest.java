package com.ikea.assignment.product_information_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikea.assignment.product_information_system.model.Country;
import com.ikea.assignment.product_information_system.model.Product;
import com.ikea.assignment.product_information_system.services.ProductsService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;

    @Autowired
    private ObjectMapper objectMapper;

    Product product;

    @BeforeEach
    public void setup() {
        product = new Product();
        product.setSku("sku");
        product.setTitle("title");
        product.setPrice(BigDecimal.TEN);
        product.setCountry(Country.EU);
        product.setDiscountPercentage(2.0);
    }

    //Get Controller
    @Test
    @Order(1)
    public void getProductTest() throws Exception{
        // precondition
        List<Product> products = new ArrayList<>();
        products.add(product);
        Product newProduct = new Product();
        newProduct.setSku("sku1");
        newProduct.setTitle("title1");
        newProduct.setPrice(BigDecimal.ONE);
        newProduct.setCountry(Country.IN);
        newProduct.setDiscountPercentage(2.0);
        products.add(newProduct);
        given(productsService.getAllProducts()).willReturn(products);

        // action
        ResultActions response = mockMvc.perform(get("/products"));

        // verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(products.size())));

    }

    //get by Id controller
    @Test
    @Order(2)
    public void getByIdProductTest() throws Exception{
        // precondition
        given(productsService.getProductBy(product.getSku())).willReturn(product);

        // action
        ResultActions response = mockMvc.perform(get("/products/{sku}", product.getSku()));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title", is(product.getTitle())))
                .andExpect(jsonPath("$.sku", is(product.getSku())));
    }



    @Test
    @Order(3)
    public void updateProductTest() throws Exception{
        // precondition
        given(productsService.getProductBy(product.getSku())).willReturn(product);
        product.setPrice(BigDecimal.ONE);
        given(productsService.update(any())).willReturn(product);

        // action
        ResultActions response = mockMvc.perform(put("/products/{sku}", product.getSku())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.sku", is(product.getSku())))
                .andExpect(jsonPath("$.title", is(product.getTitle())));
    }

}