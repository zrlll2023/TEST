package com.example.app.controller;

import com.example.app.common.Response;
import com.example.app.entity.Company;
import com.example.app.entity.Job;
import com.example.app.entity.JobApplication;
import com.example.app.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<Response<List<Job>>> getJobs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String experience,
            @RequestParam(required = false) String education,
            @RequestParam(required = false) BigDecimal salaryMin,
            @RequestParam(required = false) BigDecimal salaryMax,
            @RequestParam(required = false) String keyword) {
        List<Job> jobs = jobService.getJobs(location, experience, education, salaryMin, salaryMax, keyword);
        return ResponseEntity.ok(Response.success(jobs));
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Response<Job>> getJob(@PathVariable Long jobId) {
        Job job = jobService.getJob(jobId);
        return ResponseEntity.ok(Response.success(job));
    }

    @GetMapping("/hot")
    public ResponseEntity<Response<List<Job>>> getHotJobs(@RequestParam(defaultValue = "10") Integer limit) {
        List<Job> jobs = jobService.getHotJobs(limit);
        return ResponseEntity.ok(Response.success(jobs));
    }

    @GetMapping("/recent")
    public ResponseEntity<Response<List<Job>>> getRecentJobs(@RequestParam(defaultValue = "10") Integer limit) {
        List<Job> jobs = jobService.getRecentJobs(limit);
        return ResponseEntity.ok(Response.success(jobs));
    }

    @GetMapping("/companies")
    public ResponseEntity<Response<List<Company>>> getCompanies(
            @RequestParam(required = false) String industry,
            @RequestParam(required = false) String keyword) {
        List<Company> companies = jobService.getCompanies(industry, keyword);
        return ResponseEntity.ok(Response.success(companies));
    }

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<Response<Company>> getCompany(@PathVariable Long companyId) {
        Company company = jobService.getCompany(companyId);
        return ResponseEntity.ok(Response.success(company));
    }

    @PostMapping("/{userId}/apply")
    public ResponseEntity<Response<JobApplication>> applyJob(
            @PathVariable Long userId,
            @RequestBody Map<String, Object> request) {
        Long jobId = Long.parseLong(request.get("jobId").toString());
        Long resumeId = request.get("resumeId") != null ? Long.parseLong(request.get("resumeId").toString()) : null;
        String coverLetter = (String) request.get("coverLetter");
        
        JobApplication application = jobService.applyJob(userId, jobId, resumeId, coverLetter);
        return ResponseEntity.ok(Response.success("申请成功", application));
    }

    @GetMapping("/{userId}/applications")
    public ResponseEntity<Response<List<JobApplication>>> getApplications(@PathVariable Long userId) {
        List<JobApplication> applications = jobService.getApplications(userId);
        return ResponseEntity.ok(Response.success(applications));
    }

    @GetMapping("/{userId}/applications/check")
    public ResponseEntity<Response<Boolean>> checkApplied(
            @PathVariable Long userId,
            @RequestParam Long jobId) {
        boolean hasApplied = jobService.hasApplied(userId, jobId);
        return ResponseEntity.ok(Response.success(hasApplied));
    }

}