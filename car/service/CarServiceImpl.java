package com.car.service;

import com.car.dao.CarDao;
import com.car.dao.CarDaoImpl;

import java.sql.SQLException;
import java.util.Map;

public class CarServiceImpl implements CarService {

    CarDao dao = new CarDaoImpl();

    public void displayCars() throws SQLException {

        dao.getCarInfo().forEach(System.out::println);
    }

    public void displayStats() throws SQLException {

        Map<String,Integer> map = dao.getCarStats();

        map.forEach((brand,count) ->
                System.out.println(brand + " : " + count)
        );
    }
}