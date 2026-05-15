package com.example.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_resume_analysis")
public class AiResumeAnalysis {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("resume_content")
    private String resumeContent;

    @TableField("format_suggestions")
    private String formatSuggestions;

    @TableField("skill_matching")
    private String skillMatching;

    @TableField("experience_polish")
    private String experiencePolish;

    @TableField("gap_analysis")
    private String gapAnalysis;

    @TableField("overall_score")
    private Integer overallScore;

    @TableField("created_at")
    private LocalDateTime createdAt;

}