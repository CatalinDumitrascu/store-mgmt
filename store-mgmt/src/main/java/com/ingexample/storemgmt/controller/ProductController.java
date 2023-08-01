package com.ingexample.storemgmt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping("/info")
    public ResponseEntity<?> info() {
        return new ResponseEntity<>("Store Products API is up and running!", HttpStatus.OK);
    }
}
