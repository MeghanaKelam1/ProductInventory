package com.app.inventory_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name="inventory2")
public class Inventory {

//    private Product product;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product Id must not be null")
    @Min(value = 1001, message = "product id must be greater than 1000")
    @Max(value = 9999, message = "Product ID must be less than 9999")
    private Long productId;

    @NotNull(message = "Product quantity must not be null")
    @Min(value = 0, message = "Quantity must be zero or positive")
    @Max(value = 10000, message = "Quantity must not exceed 10,000 units")
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "availability_id", referencedColumnName = "availability_id")
    private Availability availability;

    public Inventory() {
    }

    public Inventory(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

}

