package com.repository;

import com.model.User;
import com.enums.UserMembership;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    // Constructor Injection - Spring automatically passes JdbcTemplate here
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ADD user to DB
    @Transactional
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, membership) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getMembership().toString());
    }

    @Transactional
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // FETCH all users
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                UserMembership.valueOf(rs.getString("membership"))
        ));
    }
}