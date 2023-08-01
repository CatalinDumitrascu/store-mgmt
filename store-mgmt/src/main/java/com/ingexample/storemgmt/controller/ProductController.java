package com.ingexample.storemgmt.controller;

import com.ingexample.storemgmt.entity.Product;
import com.ingexample.storemgmt.helpers.CustomErrorResponse;
import com.ingexample.storemgmt.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> info() {
        log.info(LocalDateTime.now() + "Info endpoint called");
        return new ResponseEntity<>("Store Products API is up and running!", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getProducts() {
        log.info(LocalDateTime.now() + "Get all endpoint called");
        ResponseEntity<?> response = null;
        try {
            List<Product> productList = productService.getProducts();
            log.info(LocalDateTime.now() + " Successfully retrieved product list: " + productList);
            if(productList != null && productList.size() > 0) {
                response = new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(new CustomErrorResponse("404", "No items found."), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.warn(LocalDateTime.now() + " Exception encountered while trying to retrieve product list. Exception: " + e.getMessage());
            response = new ResponseEntity<>(new CustomErrorResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        log.info(LocalDateTime.now() + "Get product by id endpoint called");
        ResponseEntity<?> response = null;
        try {
            Product product = productService.getProductById(id);
            log.info(LocalDateTime.now() + " Successfully retrieved product with id: " + id + "; Product: \n" + product);
            if(product != null) {
                response = new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(new CustomErrorResponse("404", "No product found for id = " + id), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.warn(LocalDateTime.now() + " Exception encountered while trying to retrieve product with id: " + id + "; Exception: " + e.getMessage());
            response = new ResponseEntity<>(new CustomErrorResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PostMapping()
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        log.info(LocalDateTime.now() + " Post endpoint for creating product called");
        ResponseEntity<?> response = null;

        try {
            productService.addProduct(product);
            log.info(LocalDateTime.now() + "; Successfully created entry for product:\n" + product);
            response = new ResponseEntity<>("Product added successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            log.warn(LocalDateTime.now() + " Exception encountered while trying to create product. Exception: " + e.getMessage());
            response = new ResponseEntity<>(new CustomErrorResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
                                           @Valid @RequestBody Product product) {
        log.info(LocalDateTime.now() + " Put endpoint for updating product called");
        ResponseEntity<?> response = null;

        try {
            Product updatedProduct = productService.updateProduct(id, product);
            if(updatedProduct != null) {
                log.info(LocalDateTime.now() + " Successfully retrieved product with id: " + id + "; Product: \n" + product);
                response = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(new CustomErrorResponse("404", "No product found to update for id = " + id),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.warn(LocalDateTime.now() + " Exception encountered while trying to update product. Exception: " + e.getMessage());
            response = new ResponseEntity<>(new CustomErrorResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
