package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.AiResumeAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AiResumeAnalysisMapper extends BaseMapper<AiResumeAnalysis> {

    List<AiResumeAnalysis> findByUserId(@Param("userId") Long userId);

}