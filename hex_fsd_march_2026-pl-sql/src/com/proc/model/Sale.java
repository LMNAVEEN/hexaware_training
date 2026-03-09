package com.proc.model;

public class Sale {

    private int id;
    private String productName;
    private String category;
    private int quantity;
    private double price;
    private String dateOfSale;

    public Sale(int id, String productName, String category, int quantity, double price, String dateOfSale) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.dateOfSale = dateOfSale;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", dateOfSale='" + dateOfSale + '\'' +
                '}';
    }
}