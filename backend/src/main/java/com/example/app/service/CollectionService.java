package com.example.app.service;

import com.example.app.dto.request.CollectionRequest;
import com.example.app.entity.UserCollection;

import java.util.List;

public interface CollectionService {

    void addCollection(Long userId, CollectionRequest request);

    void removeCollection(Long userId, String collectionType, Long targetId);

    boolean isCollected(Long userId, String collectionType, Long targetId);

    List<UserCollection> getCollections(Long userId, String collectionType);

    List<UserCollection> getAllCollections(Long userId);

}