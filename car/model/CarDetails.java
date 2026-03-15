package com.car.model;

public class CarDetails {

    private int id;
    private int yearOfPurchase;
    private int mileage;

    public CarDetails(int id, int yearOfPurchase, int mileage) {
        this.id = id;
        this.yearOfPurchase = yearOfPurchase;
        this.mileage = mileage;
    }

    public int getYearOfPurchase() {
        return yearOfPurchase;
    }

    public int getMileage() {
        return mileage;
    }
}