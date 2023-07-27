package com.learning.spring.demo.services;

import com.learning.spring.demo.exceptions.handler.NoSuchProductFoundException;
import com.learning.spring.demo.models.ProductModel;
import com.learning.spring.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductModel findProductById(UUID id) throws NoSuchElementException {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchProductFoundException(
                                String.format("Product {id=%s} not found.", id)));
    }

    @Override
    public ProductModel saveProduct(ProductModel productModel){
        return productRepository.save(productModel);
    }

    @Override
    public List<ProductModel> findAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(UUID id){

        findProductById(id);

        productRepository.deleteById(id);
    }
}
