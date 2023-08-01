package com.learning.spring.demo.domain.service;

import com.learning.spring.demo.domain.model.ProductModel;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface ProductService {

    ProductModel findProductById(UUID id) throws NoSuchElementException;

    ProductModel saveProduct(ProductModel productModel);

    ProductModel updateProduct(ProductModel productModel);

    List<ProductModel> findAllProducts();

    void deleteProduct(UUID id);
}
