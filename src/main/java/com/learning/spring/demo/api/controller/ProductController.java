package com.learning.spring.demo.api.controller;

import com.learning.spring.demo.api.model.ProductRecordDto;
import com.learning.spring.demo.domain.model.ProductModel;
import com.learning.spring.demo.domain.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.saveProduct(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable(value = "id") UUID id) {

        ProductModel productModel = productService.findProductById(id);

        return ResponseEntity.status(HttpStatus.OK).body(productModel);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(
            @PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto) {

        ProductModel productModel = productService.findProductById(id);
        BeanUtils.copyProperties(productRecordDto, productModel);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.saveProduct(productModel));

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
