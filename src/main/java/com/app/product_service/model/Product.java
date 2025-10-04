package com.app.product_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "product2")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name must not be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Product name must contain only letters and spaces")
    @Size(max = 30, message = "Product name must not exceed 30 characters")
    @Column(name = "product_name") // maps to column in DB
    private String productName;

    @NotBlank(message = "Product description must not be blank")
    private String description;

    @NotNull(message = "Price must not be null")
    @Min(value = 1, message = "Price must be greater than zero")
    private Double price;

    public Product() {}

    public Product(String productName, String description, Double price) {
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}