package com.example.app.controller;

import com.example.app.common.Response;
import com.example.app.dto.request.LoginRequest;
import com.example.app.dto.request.RegisterRequest;
import com.example.app.entity.User;
import com.example.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response<User>> register(@Valid @RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.ok(Response.success(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<User>> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.login(request);
        return ResponseEntity.ok(Response.success(user));
    }

}