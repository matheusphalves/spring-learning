package com.learning.spring.demo.domain.service;

import com.learning.spring.demo.domain.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class ProductServiceImplTest {


    @Autowired
    private ProductService productService;

    @Test
    void saveProductShouldReturnCreatedProductModel() {

        String productName = "Product Test";
        BigDecimal productValue = new BigDecimal("12.0");

        ProductModel productModel = new ProductModel();
        productModel.setName(productName);
        productModel.setValue(productValue);

        ProductModel createdProductModel = productService.saveProduct(productModel);

        assertNotNull(createdProductModel.getIdProduct());
        assertNotNull(createdProductModel.getCreatedAt());
        assertNull(createdProductModel.getUpdatedAt());
        assertEquals(productName, createdProductModel.getName());
        assertEquals(productValue, createdProductModel.getValue());

    }
}
