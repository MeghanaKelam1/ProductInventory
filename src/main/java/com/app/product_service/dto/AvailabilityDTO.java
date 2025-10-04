package com.app.product_service.dto;

public class AvailabilityDTO {

    private Integer availableQuantity;
    private String storeType;
    private Integer deliveryTime;

    public AvailabilityDTO() {
    }

    public AvailabilityDTO(Integer availableQuantity, String store, Integer deliveryTime) {
        this.availableQuantity = availableQuantity;
        this.storeType = store; // map store to storeType
        this.deliveryTime = deliveryTime;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "AvailabilityDTO{" +
                "availableQuantity=" + availableQuantity +
                ", storeType='" + storeType + '\'' +
                ", deliveryTime=" + deliveryTime +
                '}';
    }
}