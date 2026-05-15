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
@TableName("question")
public class Question {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("category_id")
    private Long categoryId;

    @TableField("question_type")
    private String questionType;

    @TableField("difficulty")
    private Integer difficulty;

    @TableField("content")
    private String content;

    @TableField("options")
    private String options;

    @TableField("answer")
    private String answer;

    @TableField("analysis")
    private String analysis;

    @TableField("tags")
    private String tags;

    @TableField("view_count")
    private Integer viewCount = 0;

    @TableField("like_count")
    private Integer likeCount = 0;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

}