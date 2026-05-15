package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.AiInterview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AiInterviewMapper extends BaseMapper<AiInterview> {

    List<AiInterview> findByUserId(@Param("userId") Long userId);

    List<AiInterview> findByStatus(@Param("status") String status);

}