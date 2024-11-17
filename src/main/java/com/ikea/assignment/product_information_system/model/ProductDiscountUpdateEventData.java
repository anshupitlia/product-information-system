package com.ikea.assignment.product_information_system.model;

public class ProductDiscountUpdateEventData {
    private String country;
    private String oldDiscountPercentage;
    private String newDiscountPercentage;
    private String sku;
    private String timestamp;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOldDiscountPercentage() {
        return oldDiscountPercentage;
    }

    public void setOldDiscountPercentage(String oldDiscountPercentage) {
        this.oldDiscountPercentage = oldDiscountPercentage;
    }

    public String getNewDiscountPercentage() {
        return newDiscountPercentage;
    }

    public void setNewDiscountPercentage(String newDiscountPercentage) {
        this.newDiscountPercentage = newDiscountPercentage;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ProductDiscountUpdateEventData(String country, String oldDiscountPercentage, String newDiscountPercentage, String sku, String timestamp) {
        this.country = country;
        this.oldDiscountPercentage = oldDiscountPercentage;
        this.newDiscountPercentage = newDiscountPercentage;
        this.sku = sku;
        this.timestamp = timestamp;
    }
}
