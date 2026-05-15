package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.QuestionCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionCategoryMapper extends BaseMapper<QuestionCategory> {

    List<QuestionCategory> findByParentId(@Param("parentId") Long parentId);

    List<QuestionCategory> findAllWithCount();

}