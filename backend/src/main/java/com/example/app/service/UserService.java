package com.example.app.service;

import com.example.app.dto.request.LoginRequest;
import com.example.app.dto.request.RegisterRequest;
import com.example.app.dto.request.UpdateProfileRequest;
import com.example.app.entity.User;
import com.example.app.entity.UserProfile;

public interface UserService {

    User register(RegisterRequest request);

    User login(LoginRequest request);

    User getUserById(Long userId);

    UserProfile getProfile(Long userId);

    UserProfile updateProfile(Long userId, UpdateProfileRequest request);

    void changePassword(Long userId, String oldPassword, String newPassword);

    UserProfile createProfile(Long userId);

}