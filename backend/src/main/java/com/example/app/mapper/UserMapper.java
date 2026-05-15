package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByPhone(@Param("phone") String phone);

    Optional<User> findByUsername(@Param("username") String username);

}