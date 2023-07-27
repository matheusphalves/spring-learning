package com.learning.spring.demo.domain.service;

import com.learning.spring.demo.domain.exception.NoSuchProductFoundException;
import com.learning.spring.demo.domain.model.ProductModel;
import com.learning.spring.demo.domain.repository.ProductRepository;
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
