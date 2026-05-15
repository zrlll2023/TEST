package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.JobApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobApplicationMapper extends BaseMapper<JobApplication> {

    List<JobApplication> findByUserId(@Param("userId") Long userId);

    List<JobApplication> findByJobId(@Param("jobId") Long jobId);

    JobApplication findByUserIdAndJobId(@Param("userId") Long userId, @Param("jobId") Long jobId);

    int countByUserId(@Param("userId") Long userId);

}