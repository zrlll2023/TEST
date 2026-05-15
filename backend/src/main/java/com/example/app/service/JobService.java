package com.example.app.service;

import com.example.app.entity.Company;
import com.example.app.entity.Job;
import com.example.app.entity.JobApplication;

import java.math.BigDecimal;
import java.util.List;

public interface JobService {

    List<Job> getJobs(String location, String experience, String education,
                      BigDecimal salaryMin, BigDecimal salaryMax, String keyword);

    Job getJob(Long jobId);

    List<Job> getHotJobs(Integer limit);

    List<Job> getRecentJobs(Integer limit);

    List<Company> getCompanies(String industry, String keyword);

    Company getCompany(Long companyId);

    JobApplication applyJob(Long userId, Long jobId, Long resumeId, String coverLetter);

    List<JobApplication> getApplications(Long userId);

    boolean hasApplied(Long userId, Long jobId);

}