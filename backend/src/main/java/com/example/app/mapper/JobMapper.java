package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface JobMapper extends BaseMapper<Job> {

    List<Job> findByCompanyId(@Param("companyId") Long companyId);

    List<Job> findByLocation(@Param("location") String location);

    List<Job> findByExperience(@Param("experience") String experience);

    List<Job> findByEducation(@Param("education") String education);

    List<Job> findBySalaryRange(@Param("salaryMin") BigDecimal salaryMin, @Param("salaryMax") BigDecimal salaryMax);

    List<Job> findByDeadlineAfter(@Param("deadline") LocalDateTime deadline);

    List<Job> search(@Param("keyword") String keyword);

    List<Job> findHotJobs(@Param("limit") Integer limit);

    List<Job> findRecentJobs(@Param("limit") Integer limit);

    int incrementViewCount(@Param("jobId") Long jobId);

    int incrementApplyCount(@Param("jobId") Long jobId);

}