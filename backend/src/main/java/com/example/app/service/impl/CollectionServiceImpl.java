package com.example.app.service.impl;

import com.example.app.dto.request.CollectionRequest;
import com.example.app.entity.UserCollection;
import com.example.app.mapper.UserCollectionMapper;
import com.example.app.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final UserCollectionMapper userCollectionMapper;

    @Override
    @Transactional
    public void addCollection(Long userId, CollectionRequest request) {
        if (isCollected(userId, request.getCollectionType(), request.getTargetId())) {
            return;
        }

        UserCollection collection = new UserCollection();
        collection.setUserId(userId);
        collection.setCollectionType(request.getCollectionType());
        collection.setTargetId(request.getTargetId());
        collection.setCreatedAt(LocalDateTime.now());
        userCollectionMapper.insert(collection);
    }

    @Override
    @Transactional
    public void removeCollection(Long userId, String collectionType, Long targetId) {
        userCollectionMapper.deleteByUserIdAndTargetId(userId, targetId, collectionType);
    }

    @Override
    public boolean isCollected(Long userId, String collectionType, Long targetId) {
        return userCollectionMapper.countByUserIdAndTargetId(userId, targetId, collectionType) > 0;
    }

    @Override
    public List<UserCollection> getCollections(Long userId, String collectionType) {
        return userCollectionMapper.findByUserIdAndType(userId, collectionType);
    }

    @Override
    public List<UserCollection> getAllCollections(Long userId) {
        return userCollectionMapper.findByUserId(userId);
    }

}