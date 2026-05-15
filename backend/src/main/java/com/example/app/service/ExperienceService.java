package com.example.app.service;

import com.example.app.entity.CompanyReview;
import com.example.app.entity.ExperienceComment;
import com.example.app.entity.InterviewExperience;

import java.util.List;
import java.util.Map;

public interface ExperienceService {

    InterviewExperience createExperience(Long userId, InterviewExperience experience);

    InterviewExperience getExperience(Long experienceId);

    List<InterviewExperience> getExperiences(Long companyId, String result, String keyword);

    List<InterviewExperience> getHotExperiences(Integer limit);

    List<InterviewExperience> getRecentExperiences(Integer limit);

    void likeExperience(Long userId, Long experienceId);

    ExperienceComment addComment(Long userId, Long experienceId, String content, Long parentId);

    List<ExperienceComment> getComments(Long experienceId);

    void likeComment(Long commentId);

    CompanyReview createReview(Long userId, CompanyReview review);

    List<CompanyReview> getReviews(Long companyId);

    Map<String, Object> getCompanyStats(Long companyId);

    String generateAiSummary(Long experienceId);

}