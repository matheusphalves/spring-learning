package com.learning.spring.demo.domain.service;

import com.learning.spring.demo.domain.exception.NoSuchProductFoundException;
import com.learning.spring.demo.domain.model.ProductModel;
import com.learning.spring.demo.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {


    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Mock
    private ProductRepository productRepository;

    private ProductModel productModel;

    @BeforeEach
    public void setUp(){
        productModel = new ProductModel();
        productModel.setIdProduct(UUID.randomUUID());
        productModel.setName("Name");
        productModel.setValue(new BigDecimal("12.0"));
        productModel.setDiscountRate(2L);
    }

    @Test
    void saveProductShouldReturnCreatedProductModel() {

        when(productRepository.save(productModel)).thenReturn(productModel);

        ProductModel createdProductModel = productServiceImpl.saveProduct(productModel);

        assertNotNull(createdProductModel.getIdProduct());
        assertNotNull(createdProductModel.getCreatedAt());
        assertNull(createdProductModel.getUpdatedAt());
        assertEquals("Name", createdProductModel.getName());
        assertEquals(new BigDecimal("12.0"), createdProductModel.getValue());

    }

    @Test
    void findProductByIdShouldThrowsNoSuchProductFoundExceptionWhenProductIsNotFound(){

        UUID id = UUID.randomUUID();

        when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchProductFoundException.class,
                () -> productServiceImpl.findProductById(id));

    }

    @Test
    void findAllProductsShouldReturnListOfProductModel(){
        when(productRepository.findAll())
                .thenReturn(Collections.singletonList(productModel));

        List<ProductModel> productModels = productServiceImpl.findAllProducts();

        assertEquals(1, productModels.size());

    }

    @Test
    void deleteProductShouldDeleteExistingProductModel(){
        UUID id = UUID.randomUUID();

        when(productRepository.findById(id)).thenReturn(Optional.of(productModel));

        productServiceImpl.deleteProduct(id);

        verify(productRepository).deleteById(id);

    }
}
