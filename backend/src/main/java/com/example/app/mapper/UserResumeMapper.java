package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.UserResume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserResumeMapper extends BaseMapper<UserResume> {

    List<UserResume> findByUserId(@Param("userId") Long userId);

    Optional<UserResume> findDefaultByUserId(@Param("userId") Long userId);

}