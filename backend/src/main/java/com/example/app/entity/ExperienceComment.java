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
@TableName("experience_comment")
public class ExperienceComment {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("experience_id")
    private Long experienceId;

    @TableField("content")
    private String content;

    @TableField("like_count")
    private Integer likeCount = 0;

    @TableField("parent_id")
    private Long parentId;

    @TableField("created_at")
    private LocalDateTime createdAt;

}