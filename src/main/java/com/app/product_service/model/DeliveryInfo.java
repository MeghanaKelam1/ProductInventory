package com.app.product_service.model;

public class DeliveryInfo {
    private String productName;
    private Integer requestedQuantity;
    private Integer availableQuantity;
    private String storeType;
    private Integer deliveryTime;

    public DeliveryInfo() {
    }

    public DeliveryInfo(String productName, Integer requestedQuantity, Integer availableQuantity, String storeType, Integer deliveryTime) {
        this.productName = productName;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
        this.storeType = storeType;
        this.deliveryTime = deliveryTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
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
}
