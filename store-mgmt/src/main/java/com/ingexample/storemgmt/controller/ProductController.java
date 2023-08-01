package com.ingexample.storemgmt.controller;

import com.ingexample.storemgmt.entity.Product;
import com.ingexample.storemgmt.helpers.CustomErrorResponse;
import com.ingexample.storemgmt.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

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
        ResponseEntity<?> response = null;
        try {
            List<Product> productList = productService.getProducts();
            if(productList != null && productList.size() > 0) {
                response = new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(new CustomErrorResponse("404", "No items found."), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(new CustomErrorResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ResponseEntity<?> response = null;
        try {
            Product product = productService.getProductById(id);
            if(product != null) {
                response = new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(new CustomErrorResponse("404", "No product found for id = " + id), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(new CustomErrorResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
