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
@TableName("ai_interview")
public class AiInterview {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("target_position")
    private String targetPosition;

    @TableField("target_company")
    private String targetCompany;

    @TableField("status")
    private String status;

    @TableField("score")
    private Integer score;

    @TableField("duration")
    private Integer duration;

    @TableField("questions")
    private String questions;

    @TableField("answers")
    private String answers;

    @TableField("feedback")
    private String feedback;

    @TableField("report")
    private String report;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

}