package com.springboot.task.controller;


import com.springboot.task.utility.JwtUtility;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final JwtUtility jwtUtility;

    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal) {

        String username = principal.getName();

        Map<String, String> map = new HashMap<>();
        map.put("token", jwtUtility.generateToken(username));

        return ResponseEntity.ok(map);
    }
}