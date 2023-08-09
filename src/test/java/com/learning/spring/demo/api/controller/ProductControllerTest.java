package com.learning.spring.demo.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.spring.demo.api.model.ProductRecordDto;
import com.learning.spring.demo.domain.model.ProductModel;
import com.learning.spring.demo.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private ProductRecordDto productRecordDto;

    @BeforeEach
    void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .alwaysDo(print())
                .build();

        objectMapper = new ObjectMapper();

        productRecordDto = new ProductRecordDto(
                "name",
                new BigDecimal("12.0"),
                new BigDecimal("0.5"),
                null,null
        );

    }

    @Test
    void shouldSaveProduct() throws Exception {

        when(productService.saveProduct(any(ProductModel.class)))
                .thenReturn(any(ProductModel.class));

        mockMvc.perform(post("/products")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRecordDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        verify(productService).saveProduct(any(ProductModel.class));
    }

    @Test
    void shouldReturnListOfProducts() throws Exception{
        mockMvc.perform(get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
