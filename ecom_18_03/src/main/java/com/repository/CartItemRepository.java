package com.repository;

import com.model.CartItem;
import com.model.User;
import com.enums.UserMembership;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class CartItemRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    // Constructor Injection - both dependencies injected by Spring
    public CartItemRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    // ADD item to DB
    @Transactional
    public void addItem(CartItem item) {
        String sql = "INSERT INTO cart_items (name, price, qty, user_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                item.getName(),
                item.getPrice(),
                item.getQty(),
                item.getUser().getId()   // only save user_id in DB
        );
    }

    // FETCH all items
    public List<CartItem> getAllItems() {
        String sql = "SELECT * FROM cart_items";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = getUserById(rs.getInt("user_id"));
            return new CartItem(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getInt("qty"),
                    user
            );
        });
    }

    // FETCH items by username
    public List<CartItem> getItemsByUsername(String username) {
        String sql = "SELECT c.* FROM cart_items c " +
                "JOIN users u ON c.user_id = u.id " +
                "WHERE u.name = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = getUserById(rs.getInt("user_id"));
            return new CartItem(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getInt("qty"),
                    user
            );
        }, username);
    }

    // DELETE item by id
    @Transactional
    public void deleteItem(int id) {
        String sql = "DELETE FROM cart_items WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Helper - get User object from user_id
    private User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                UserMembership.valueOf(rs.getString("membership"))
        ), id);
    }
}