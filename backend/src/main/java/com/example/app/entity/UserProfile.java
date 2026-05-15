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
@TableName("user_profile")
public class UserProfile {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("real_name")
    private String realName;

    @TableField("school")
    private String school;

    @TableField("major")
    private String major;

    @TableField("grade")
    private String grade;

    @TableField("skills")
    private String skills;

    @TableField("github_url")
    private String githubUrl;

    @TableField("blog_url")
    private String blogUrl;

    @TableField("bio")
    private String bio;

    @TableField("target_company")
    private String targetCompany;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

}