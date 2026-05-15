package com.example.app.service.impl;

import com.example.app.common.BusinessException;
import com.example.app.entity.CompanyReview;
import com.example.app.entity.ExperienceComment;
import com.example.app.entity.InterviewExperience;
import com.example.app.mapper.CompanyReviewMapper;
import com.example.app.mapper.ExperienceCommentMapper;
import com.example.app.mapper.InterviewExperienceMapper;
import com.example.app.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final InterviewExperienceMapper experienceMapper;
    private final ExperienceCommentMapper commentMapper;
    private final CompanyReviewMapper reviewMapper;

    @Override
    @Transactional
    public InterviewExperience createExperience(Long userId, InterviewExperience experience) {
        experience.setUserId(userId);
        experience.setCreatedAt(LocalDateTime.now());
        experience.setUpdatedAt(LocalDateTime.now());
        experienceMapper.insert(experience);
        
        String aiSummary = generateAiSummary(experience.getId());
        experience.setAiSummary(aiSummary);
        experienceMapper.updateById(experience);
        
        return experience;
    }

    @Override
    public InterviewExperience getExperience(Long experienceId) {
        InterviewExperience experience = experienceMapper.selectById(experienceId)
                .orElseThrow(() -> new BusinessException(404, "面经不存在"));
        experienceMapper.incrementViewCount(experienceId);
        return experience;
    }

    @Override
    public List<InterviewExperience> getExperiences(Long companyId, String result, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return experienceMapper.search(keyword);
        }
        if (companyId != null) {
            return experienceMapper.findByCompanyId(companyId);
        }
        if (result != null) {
            return experienceMapper.findByResult(result);
        }
        return experienceMapper.selectList(null);
    }

    @Override
    public List<InterviewExperience> getHotExperiences(Integer limit) {
        return experienceMapper.findHotExperiences(limit);
    }

    @Override
    public List<InterviewExperience> getRecentExperiences(Integer limit) {
        return experienceMapper.findRecentExperiences(limit);
    }

    @Override
    @Transactional
    public void likeExperience(Long userId, Long experienceId) {
        experienceMapper.incrementLikeCount(experienceId);
    }

    @Override
    @Transactional
    public ExperienceComment addComment(Long userId, Long experienceId, String content, Long parentId) {
        ExperienceComment comment = new ExperienceComment();
        comment.setUserId(userId);
        comment.setExperienceId(experienceId);
        comment.setContent(content);
        comment.setParentId(parentId);
        comment.setCreatedAt(LocalDateTime.now());
        commentMapper.insert(comment);
        
        experienceMapper.incrementCommentCount(experienceId);
        
        return comment;
    }

    @Override
    public List<ExperienceComment> getComments(Long experienceId) {
        return commentMapper.findByExperienceId(experienceId);
    }

    @Override
    @Transactional
    public void likeComment(Long commentId) {
        commentMapper.incrementLikeCount(commentId);
    }

    @Override
    @Transactional
    public CompanyReview createReview(Long userId, CompanyReview review) {
        review.setUserId(userId);
        review.setCreatedAt(LocalDateTime.now());
        reviewMapper.insert(review);
        return review;
    }

    @Override
    public List<CompanyReview> getReviews(Long companyId) {
        return reviewMapper.findByCompanyId(companyId);
    }

    @Override
    public Map<String, Object> getCompanyStats(Long companyId) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("averageRating", reviewMapper.getAverageRating(companyId));
        stats.put("reviewCount", reviewMapper.countByCompanyId(companyId));
        return stats;
    }

    @Override
    public String generateAiSummary(Long experienceId) {
        InterviewExperience experience = experienceMapper.selectById(experienceId).orElse(null);
        if (experience == null) {
            return "";
        }
        
        return "【面试总结】\n" +
               "- 公司：" + experience.getCompanyName() + "\n" +
               "- 职位：" + experience.getPosition() + "\n" +
               "- 面试结果：" + experience.getResult() + "\n" +
               "- 难度评级：" + experience.getDifficulty() + "星\n" +
               "- 薪资范围：" + experience.getSalaryRange() + "\n\n" +
               "面试要点：请查看原文获取详细内容。";
    }

}