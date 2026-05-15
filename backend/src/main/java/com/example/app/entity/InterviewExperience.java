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
@TableName("interview_experience")
public class InterviewExperience {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("company_id")
    private Long companyId;

    @TableField("company_name")
    private String companyName;

    @TableField("position")
    private String position;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("ai_summary")
    private String aiSummary;

    @TableField("tags")
    private String tags;

    @TableField("interview_date")
    private LocalDateTime interviewDate;

    @TableField("result")
    private String result;

    @TableField("salary_range")
    private String salaryRange;

    @TableField("difficulty")
    private Integer difficulty;

    @TableField("view_count")
    private Integer viewCount = 0;

    @TableField("like_count")
    private Integer likeCount = 0;

    @TableField("comment_count")
    private Integer commentCount = 0;

    @TableField("status")
    private Integer status = 1;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

}