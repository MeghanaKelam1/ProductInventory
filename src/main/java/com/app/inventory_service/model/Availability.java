package com.app.inventory_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "availability2")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;

    @NotNull(message = "Availability ID must not be null")
    @Column(name = "availability_id", unique = true)
    private Long availabilityId;

    @NotNull(message = "Delivery time must not be null")
    @Min(value = 0, message = "Delivery time must be zero or positive")
    @Column(name = "delivery_time")
    private Integer deliveryTime;

    @NotBlank(message = "Store type must not be blank")
    @Pattern(regexp = "dark store|normal store", message = "Store must be either 'dark store' or 'normal store'")
    @Column(name = "store")
    private String store;

    // Constructors
    public Availability() {}

    public Availability(Long availabilityId, Integer deliveryTime, String store) {
        this.availabilityId = availabilityId;
        this.deliveryTime = deliveryTime;
        this.store = store;
    }

     //Getters and Setters
    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Long getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Long availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}