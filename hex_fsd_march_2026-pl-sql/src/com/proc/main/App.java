package com.proc.main;

import com.proc.db.SalesDB;
import com.proc.model.Sale;

import java.util.List;

public class App {

    public static void main(String[] args) {

        SalesDB salesDB = new SalesDB();

        salesDB.dbConnect();

        System.out.println("----------GET SALES BY CATEGORY-----------");

        List<Sale> list = salesDB.getSalesByCategory("Defense");
        list.forEach(System.out::println);

        System.out.println("----------TOTAL SALES-----------");

        int total = salesDB.getTotalSales("Defense");

        System.out.println("Total sales in Defense = " + total);

        System.out.println("----------SALES VIEW-----------");

        salesDB.getSalesView();

        salesDB.dbClose();
    }
}