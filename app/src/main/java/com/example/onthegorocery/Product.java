package com.example.onthegorocery;

public class Product {
    private String prName;
    private String size;
    private String price;
    private String prImg;

    public Product(String prName, String size, String price, String prImg) {
        this.prName = prName;
        this.size = size;
        this.price = price;
        this.prImg = prImg;

    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrImg() {
        return prImg;
    }

    public void setPrImg(String prImg) {
        this.prImg = prImg;
    }
}
