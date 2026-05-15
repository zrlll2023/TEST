package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.UserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfile> {

    Optional<UserProfile> findByUserId(@Param("userId") Long userId);

}