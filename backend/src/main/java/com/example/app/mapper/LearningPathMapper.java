package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.LearningPath;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LearningPathMapper extends BaseMapper<LearningPath> {

    List<LearningPath> findByTarget(@Param("target") String target);

    List<LearningPath> findByDifficulty(@Param("difficulty") Integer difficulty);

}