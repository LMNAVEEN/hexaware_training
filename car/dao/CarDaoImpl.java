package com.car.dao;

import com.car.util.DBConnection;

import java.sql.*;
import java.util.*;

public class CarDaoImpl implements CarDao {

    Connection conn = DBConnection.getConnection();

    public List<String> getCarInfo() throws SQLException {

        List<String> list = new ArrayList<>();

        String query = """
                SELECT c.brand,o.name,cd.year_of_purchase,cd.milage
                FROM car c
                JOIN owner o ON c.owner_id = o.id
                JOIN car_details cd ON c.car_details_id = cd.id
                """;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()) {

            String data =
                    rs.getString("brand") + " | " +
                    rs.getString("name") + " | " +
                    rs.getInt("year_of_purchase") + " | " +
                    rs.getInt("milage");

            list.add(data);
        }

        return list;
    }

    public Map<String,Integer> getCarStats() throws SQLException {

        Map<String,Integer> map = new HashMap<>();

        String query =
                "SELECT brand, COUNT(*) as total FROM car GROUP BY brand";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()) {

            map.put(
                    rs.getString("brand"),
                    rs.getInt("total")
            );
        }

        return map;
    }
}