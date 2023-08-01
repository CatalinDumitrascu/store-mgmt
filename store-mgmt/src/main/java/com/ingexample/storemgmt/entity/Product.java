package com.ingexample.storemgmt.entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Product {

    private Long id;
    private String name;
    private Double price;
    private int availableQuantity;
    private LocalDate expirationDate;

    public Product(Long id, String name, Double price, int availableQuantity, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.expirationDate = expirationDate;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return availableQuantity == product.availableQuantity && id.equals(product.id) && name.equals(product.name) && price.equals(product.price) && expirationDate.equals(product.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, availableQuantity, expirationDate);
    }
}
