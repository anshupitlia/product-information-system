package com.ikea.assignment.product_information_system.model;

public class ProductPriceUpdateEventData {
    private String country;
    private String oldPrice;
    private String newPrice;
    private String sku;
    private String timestamp;

    public ProductPriceUpdateEventData(String country, String oldPrice, String newPrice, String sku, String timestamp) {
        this.country = country;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.sku = sku;
        this.timestamp = timestamp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
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
}
