package com.example.app.controller;

import com.example.app.common.Response;
import com.example.app.dto.request.CollectionRequest;
import com.example.app.dto.request.UpdateProfileRequest;
import com.example.app.entity.User;
import com.example.app.entity.UserCollection;
import com.example.app.entity.UserProfile;
import com.example.app.service.CollectionService;
import com.example.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CollectionService collectionService;

    @GetMapping("/{userId}")
    public ResponseEntity<Response<User>> getUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(Response.success(user));
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<Response<UserProfile>> getProfile(@PathVariable Long userId) {
        UserProfile profile = userService.getProfile(userId);
        return ResponseEntity.ok(Response.success(profile));
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<Response<UserProfile>> updateProfile(
            @PathVariable Long userId,
            @RequestBody UpdateProfileRequest request) {
        UserProfile profile = userService.updateProfile(userId, request);
        return ResponseEntity.ok(Response.success(profile));
    }

    @PostMapping("/{userId}/change-password")
    public ResponseEntity<Response<Void>> changePassword(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request) {
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
        userService.changePassword(userId, oldPassword, newPassword);
        return ResponseEntity.ok(Response.success("密码修改成功", null));
    }

    @PostMapping("/{userId}/collections")
    public ResponseEntity<Response<Void>> addCollection(
            @PathVariable Long userId,
            @Valid @RequestBody CollectionRequest request) {
        collectionService.addCollection(userId, request);
        return ResponseEntity.ok(Response.success("收藏成功", null));
    }

    @DeleteMapping("/{userId}/collections")
    public ResponseEntity<Response<Void>> removeCollection(
            @PathVariable Long userId,
            @RequestParam String collectionType,
            @RequestParam Long targetId) {
        collectionService.removeCollection(userId, collectionType, targetId);
        return ResponseEntity.ok(Response.success("取消收藏成功", null));
    }

    @GetMapping("/{userId}/collections")
    public ResponseEntity<Response<List<UserCollection>>> getCollections(
            @PathVariable Long userId,
            @RequestParam(required = false) String collectionType) {
        List<UserCollection> collections;
        if (collectionType != null) {
            collections = collectionService.getCollections(userId, collectionType);
        } else {
            collections = collectionService.getAllCollections(userId);
        }
        return ResponseEntity.ok(Response.success(collections));
    }

    @GetMapping("/{userId}/collections/check")
    public ResponseEntity<Response<Boolean>> checkCollection(
            @PathVariable Long userId,
            @RequestParam String collectionType,
            @RequestParam Long targetId) {
        boolean isCollected = collectionService.isCollected(userId, collectionType, targetId);
        return ResponseEntity.ok(Response.success(isCollected));
    }

}