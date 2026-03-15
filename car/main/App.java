package com.car.main;

import com.car.service.CarService;
import com.car.service.CarServiceImpl;

public class App {

    public static void main(String[] args) {

        try {

            CarService service = new CarServiceImpl();

            System.out.println("-----CAR INFO-----");
            service.displayCars();

            System.out.println("\n-----CAR STATS-----");
            service.displayStats();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}