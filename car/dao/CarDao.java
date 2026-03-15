package com.car.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CarDao {

    List<String> getCarInfo() throws SQLException;

    Map<String,Integer> getCarStats() throws SQLException;
}