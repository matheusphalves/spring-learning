package com.learning.spring.demo.services;

import com.learning.spring.demo.models.ProductModel;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface ProductService {

    ProductModel findProductById(UUID id) throws NoSuchElementException;

    ProductModel saveProduct(ProductModel productModel);

    List<ProductModel> findAllProducts();

    void deleteProduct(UUID id);
}
