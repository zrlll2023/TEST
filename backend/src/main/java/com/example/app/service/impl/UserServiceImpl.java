package com.example.app.service.impl;

import com.example.app.common.BusinessException;
import com.example.app.dto.request.LoginRequest;
import com.example.app.dto.request.RegisterRequest;
import com.example.app.dto.request.UpdateProfileRequest;
import com.example.app.entity.User;
import com.example.app.entity.UserProfile;
import com.example.app.mapper.UserMapper;
import com.example.app.mapper.UserProfileMapper;
import com.example.app.service.UserService;
import com.example.app.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public User register(RegisterRequest request) {
        if (userMapper.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException(400, "邮箱已被注册");
        }
        
        if (userMapper.findByUsername(request.getUsername()).isPresent()) {
            throw new BusinessException(400, "用户名已被使用");
        }

        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            if (userMapper.findByPhone(request.getPhone()).isPresent()) {
                throw new BusinessException(400, "手机号已被注册");
            }
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setPhone(request.getPhone());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userMapper.insert(user);
        createProfile(user.getId());

        String token = jwtUtil.generateToken(user.getId());
        user.setToken(token);
        
        return user;
    }

    @Override
    public User login(LoginRequest request) {
        Optional<User> userOptional = userMapper.findByEmail(request.getAccount());
        if (userOptional.isEmpty()) {
            userOptional = userMapper.findByPhone(request.getAccount());
        }
        if (userOptional.isEmpty()) {
            userOptional = userMapper.findByUsername(request.getAccount());
        }

        if (userOptional.isEmpty()) {
            throw new BusinessException(400, "账号或密码错误");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException(400, "账号或密码错误");
        }

        if (user.getStatus() != 1) {
            throw new BusinessException(400, "账号已被禁用");
        }

        String token = jwtUtil.generateToken(user.getId());
        user.setToken(token);
        
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId)
                .orElseThrow(() -> new BusinessException(404, "用户不存在"));
    }

    @Override
    public UserProfile getProfile(Long userId) {
        return userProfileMapper.findByUserId(userId)
                .orElseGet(() -> createProfile(userId));
    }

    @Override
    @Transactional
    public UserProfile updateProfile(Long userId, UpdateProfileRequest request) {
        UserProfile profile = userProfileMapper.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(404, "用户资料不存在"));

        if (request.getRealName() != null) profile.setRealName(request.getRealName());
        if (request.getSchool() != null) profile.setSchool(request.getSchool());
        if (request.getMajor() != null) profile.setMajor(request.getMajor());
        if (request.getGrade() != null) profile.setGrade(request.getGrade());
        if (request.getSkills() != null) profile.setSkills(request.getSkills());
        if (request.getGithubUrl() != null) profile.setGithubUrl(request.getGithubUrl());
        if (request.getBlogUrl() != null) profile.setBlogUrl(request.getBlogUrl());
        if (request.getBio() != null) profile.setBio(request.getBio());
        if (request.getTargetCompany() != null) profile.setTargetCompany(request.getTargetCompany());
        
        profile.setUpdatedAt(LocalDateTime.now());
        userProfileMapper.updateById(profile);
        
        return profile;
    }

    @Override
    @Transactional
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userMapper.selectById(userId)
                .orElseThrow(() -> new BusinessException(404, "用户不存在"));
        
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BusinessException(400, "原密码错误");
        }
        
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.updateById(user);
    }

    @Override
    @Transactional
    public UserProfile createProfile(Long userId) {
        UserProfile profile = new UserProfile();
        profile.setUserId(userId);
        profile.setCreatedAt(LocalDateTime.now());
        profile.setUpdatedAt(LocalDateTime.now());
        userProfileMapper.insert(profile);
        return profile;
    }

}