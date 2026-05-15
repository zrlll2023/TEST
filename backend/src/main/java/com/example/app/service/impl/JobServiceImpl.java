package com.example.app.service.impl;

import com.example.app.common.BusinessException;
import com.example.app.entity.Company;
import com.example.app.entity.Job;
import com.example.app.entity.JobApplication;
import com.example.app.mapper.CompanyMapper;
import com.example.app.mapper.JobApplicationMapper;
import com.example.app.mapper.JobMapper;
import com.example.app.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobMapper jobMapper;
    private final CompanyMapper companyMapper;
    private final JobApplicationMapper jobApplicationMapper;

    @Override
    public List<Job> getJobs(String location, String experience, String education,
                             BigDecimal salaryMin, BigDecimal salaryMax, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return jobMapper.search(keyword);
        }
        if (location != null) {
            return jobMapper.findByLocation(location);
        }
        if (experience != null) {
            return jobMapper.findByExperience(experience);
        }
        if (education != null) {
            return jobMapper.findByEducation(education);
        }
        if (salaryMin != null || salaryMax != null) {
            return jobMapper.findBySalaryRange(salaryMin, salaryMax);
        }
        return jobMapper.selectList(null);
    }

    @Override
    public Job getJob(Long jobId) {
        Job job = jobMapper.selectById(jobId)
                .orElseThrow(() -> new BusinessException(404, "职位不存在"));
        jobMapper.incrementViewCount(jobId);
        return job;
    }

    @Override
    public List<Job> getHotJobs(Integer limit) {
        return jobMapper.findHotJobs(limit);
    }

    @Override
    public List<Job> getRecentJobs(Integer limit) {
        return jobMapper.findRecentJobs(limit);
    }

    @Override
    public List<Company> getCompanies(String industry, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return companyMapper.search(keyword);
        }
        if (industry != null) {
            return companyMapper.findByIndustry(industry);
        }
        return companyMapper.selectList(null);
    }

    @Override
    public Company getCompany(Long companyId) {
        return companyMapper.selectById(companyId)
                .orElseThrow(() -> new BusinessException(404, "公司不存在"));
    }

    @Override
    @Transactional
    public JobApplication applyJob(Long userId, Long jobId, Long resumeId, String coverLetter) {
        if (hasApplied(userId, jobId)) {
            throw new BusinessException(400, "您已申请过该职位");
        }

        Job job = jobMapper.selectById(jobId)
                .orElseThrow(() -> new BusinessException(404, "职位不存在"));

        if (job.getStatus() != 1) {
            throw new BusinessException(400, "该职位已关闭");
        }

        if (job.getDeadline() != null && job.getDeadline().isBefore(LocalDateTime.now())) {
            throw new BusinessException(400, "该职位已过截止日期");
        }

        JobApplication application = new JobApplication();
        application.setUserId(userId);
        application.setJobId(jobId);
        application.setResumeId(resumeId);
        application.setCoverLetter(coverLetter);
        application.setStatus("PENDING");
        application.setCreatedAt(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());

        jobApplicationMapper.insert(application);
        jobMapper.incrementApplyCount(jobId);

        return application;
    }

    @Override
    public List<JobApplication> getApplications(Long userId) {
        return jobApplicationMapper.findByUserId(userId);
    }

    @Override
    public boolean hasApplied(Long userId, Long jobId) {
        return jobApplicationMapper.findByUserIdAndJobId(userId, jobId) != null;
    }

}