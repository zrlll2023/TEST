package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.ExperienceComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExperienceCommentMapper extends BaseMapper<ExperienceComment> {

    List<ExperienceComment> findByExperienceId(@Param("experienceId") Long experienceId);

    List<ExperienceComment> findByParentId(@Param("parentId") Long parentId);

    List<ExperienceComment> findByUserId(@Param("userId") Long userId);

    int incrementLikeCount(@Param("commentId") Long commentId);

}