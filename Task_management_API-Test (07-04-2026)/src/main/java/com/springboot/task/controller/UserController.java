package com.springboot.task.controller;

import com.springboot.task.dto.UserResponse;
import com.springboot.task.model.User;
import com.springboot.task.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public UserResponse addUser(@RequestBody User user) {

        User savedUser = userService.insertUser(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole()
        );
    }
}