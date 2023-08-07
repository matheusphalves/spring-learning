package com.learning.spring.demo.api.controller;

import com.learning.spring.demo.api.model.ProductRecordDto;
import com.learning.spring.demo.domain.model.ProductModel;
import com.learning.spring.demo.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private ProductRecordDto productRecordDto;

    @BeforeEach
    void setup() {

        productRecordDto = new ProductRecordDto(
                "name",
                new BigDecimal("12.0"),
                new BigDecimal("0.5"),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

    }

    @Test
    void shouldSaveProduct() {

        ProductModel productModel = new ProductModel();
        productModel.setName("name");

        when(productService.saveProduct(any(ProductModel.class))).thenReturn(productModel);

        ResponseEntity<ProductModel> response =
                productController.saveProduct(productRecordDto);
    }

}
