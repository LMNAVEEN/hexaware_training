package com.car.service;

import java.sql.SQLException;

public interface CarService {

    void displayCars() throws SQLException;

    void displayStats() throws SQLException;
}