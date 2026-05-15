package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.LearningTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LearningTaskMapper extends BaseMapper<LearningTask> {

    List<LearningTask> findByPathId(@Param("pathId") Long pathId);

    List<LearningTask> findByType(@Param("type") String type);

}