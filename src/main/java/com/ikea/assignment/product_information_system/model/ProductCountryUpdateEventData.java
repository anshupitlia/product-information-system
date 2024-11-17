package com.ikea.assignment.product_information_system.model;

public class ProductCountryUpdateEventData {
    private String price;
    private String oldCountry;
    private String newCountry;
    private String sku;
    private String timestamp;

    public ProductCountryUpdateEventData(String price, String oldCountry, String newCountry, String sku, String timestamp) {
        this.price = price;
        this.oldCountry = oldCountry;
        this.newCountry = newCountry;
        this.sku = sku;
        this.timestamp = timestamp;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldCountry() {
        return oldCountry;
    }

    public void setOldCountry(String oldCountry) {
        this.oldCountry = oldCountry;
    }

    public String getNewCountry() {
        return newCountry;
    }

    public void setNewCountry(String newCountry) {
        this.newCountry = newCountry;
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
