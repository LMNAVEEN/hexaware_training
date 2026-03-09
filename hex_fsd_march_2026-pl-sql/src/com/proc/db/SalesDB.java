package com.proc.db;

import com.proc.model.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDB {

    private Connection conn;

    public void dbConnect() {

        try {

            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/hex_training?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    "root",
                    "root"
            );

            System.out.println("Database connection established");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dbClose() {

        try {

            if (conn != null)
                conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Sale> getSalesByCategory(String category) {

        List<Sale> list = new ArrayList<>();

        try {

            CallableStatement cs =
                    conn.prepareCall("{CALL get_sales_by_category(?)}");

            cs.setString(1, category);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                Sale sale = new Sale(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("date_of_sale")
                );

                list.add(sale);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public int getTotalSales(String category) {

        int total = 0;

        try {

            CallableStatement cs =
                    conn.prepareCall("{CALL count_total_sales_by_category(?,?)}");

            cs.setString(1, category);

            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();

            total = cs.getInt(2);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return total;
    }

    public void getSalesView() {

        try {

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM product_sales_summary");

            while (rs.next()) {

                String name = rs.getString("product_name");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                System.out.println(
                        "Product: " + name +
                        " | Category: " + category +
                        " | Quantity: " + quantity +
                        " | Price: " + price
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}