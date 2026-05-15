package com.example.app.controller;

import com.example.app.common.Response;
import com.example.app.entity.CompanyReview;
import com.example.app.entity.ExperienceComment;
import com.example.app.entity.InterviewExperience;
import com.example.app.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public ResponseEntity<Response<InterviewExperience>> createExperience(
            @RequestParam Long userId,
            @RequestBody InterviewExperience experience) {
        InterviewExperience created = experienceService.createExperience(userId, experience);
        return ResponseEntity.ok(Response.success("发布成功", created));
    }

    @GetMapping("/{experienceId}")
    public ResponseEntity<Response<InterviewExperience>> getExperience(@PathVariable Long experienceId) {
        InterviewExperience experience = experienceService.getExperience(experienceId);
        return ResponseEntity.ok(Response.success(experience));
    }

    @GetMapping
    public ResponseEntity<Response<List<InterviewExperience>>> getExperiences(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) String result,
            @RequestParam(required = false) String keyword) {
        List<InterviewExperience> experiences = experienceService.getExperiences(companyId, result, keyword);
        return ResponseEntity.ok(Response.success(experiences));
    }

    @GetMapping("/hot")
    public ResponseEntity<Response<List<InterviewExperience>>> getHotExperiences(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<InterviewExperience> experiences = experienceService.getHotExperiences(limit);
        return ResponseEntity.ok(Response.success(experiences));
    }

    @GetMapping("/recent")
    public ResponseEntity<Response<List<InterviewExperience>>> getRecentExperiences(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<InterviewExperience> experiences = experienceService.getRecentExperiences(limit);
        return ResponseEntity.ok(Response.success(experiences));
    }

    @PostMapping("/{experienceId}/like")
    public ResponseEntity<Response<Void>> likeExperience(
            @PathVariable Long experienceId,
            @RequestParam Long userId) {
        experienceService.likeExperience(userId, experienceId);
        return ResponseEntity.ok(Response.success("点赞成功", null));
    }

    @GetMapping("/{experienceId}/comments")
    public ResponseEntity<Response<List<ExperienceComment>>> getComments(@PathVariable Long experienceId) {
        List<ExperienceComment> comments = experienceService.getComments(experienceId);
        return ResponseEntity.ok(Response.success(comments));
    }

    @PostMapping("/{experienceId}/comments")
    public ResponseEntity<Response<ExperienceComment>> addComment(
            @PathVariable Long experienceId,
            @RequestParam Long userId,
            @RequestBody Map<String, Object> request) {
        String content = (String) request.get("content");
        Long parentId = request.get("parentId") != null ? Long.parseLong(request.get("parentId").toString()) : null;
        
        ExperienceComment comment = experienceService.addComment(userId, experienceId, content, parentId);
        return ResponseEntity.ok(Response.success("评论成功", comment));
    }

    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<Response<Void>> likeComment(@PathVariable Long commentId) {
        experienceService.likeComment(commentId);
        return ResponseEntity.ok(Response.success("点赞成功", null));
    }

    @PostMapping("/reviews")
    public ResponseEntity<Response<CompanyReview>> createReview(
            @RequestParam Long userId,
            @RequestBody CompanyReview review) {
        CompanyReview created = experienceService.createReview(userId, review);
        return ResponseEntity.ok(Response.success("评价成功", created));
    }

    @GetMapping("/reviews/company/{companyId}")
    public ResponseEntity<Response<List<CompanyReview>>> getReviews(@PathVariable Long companyId) {
        List<CompanyReview> reviews = experienceService.getReviews(companyId);
        return ResponseEntity.ok(Response.success(reviews));
    }

    @GetMapping("/company/{companyId}/stats")
    public ResponseEntity<Response<Map<String, Object>>> getCompanyStats(@PathVariable Long companyId) {
        Map<String, Object> stats = experienceService.getCompanyStats(companyId);
        return ResponseEntity.ok(Response.success(stats));
    }

}