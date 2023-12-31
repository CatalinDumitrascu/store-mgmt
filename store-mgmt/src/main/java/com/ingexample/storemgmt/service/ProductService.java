package com.ingexample.storemgmt.service;

import com.ingexample.storemgmt.entity.Product;
import com.ingexample.storemgmt.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setAvailableQuantity(updatedProduct.getAvailableQuantity());
            product.setExpirationDate(updatedProduct.getExpirationDate());
            return productRepository.saveAndFlush(product);
        }).orElse(null);
    }
}
