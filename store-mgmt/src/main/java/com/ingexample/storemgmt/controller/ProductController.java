package com.ingexample.storemgmt.controller;

import com.ingexample.storemgmt.entity.Product;
import com.ingexample.storemgmt.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> info() {
        return new ResponseEntity<>("Store Products API is up and running!", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }
}
