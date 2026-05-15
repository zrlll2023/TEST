package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.InterviewExperience;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InterviewExperienceMapper extends BaseMapper<InterviewExperience> {

    List<InterviewExperience> findByCompanyId(@Param("companyId") Long companyId);

    List<InterviewExperience> findByUserId(@Param("userId") Long userId);

    List<InterviewExperience> findByResult(@Param("result") String result);

    List<InterviewExperience> search(@Param("keyword") String keyword);

    List<InterviewExperience> findHotExperiences(@Param("limit") Integer limit);

    List<InterviewExperience> findRecentExperiences(@Param("limit") Integer limit);

    int incrementViewCount(@Param("experienceId") Long experienceId);

    int incrementLikeCount(@Param("experienceId") Long experienceId);

    int incrementCommentCount(@Param("experienceId") Long experienceId);

}