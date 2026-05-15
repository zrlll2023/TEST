package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.UserCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCollectionMapper extends BaseMapper<UserCollection> {

    List<UserCollection> findByUserId(@Param("userId") Long userId);

    List<UserCollection> findByUserIdAndType(@Param("userId") Long userId, @Param("collectionType") String collectionType);

    int countByUserIdAndTargetId(@Param("userId") Long userId, @Param("targetId") Long targetId, @Param("collectionType") String collectionType);

    int deleteByUserIdAndTargetId(@Param("userId") Long userId, @Param("targetId") Long targetId, @Param("collectionType") String collectionType);

}