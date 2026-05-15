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
@TableName("post")
public class Post {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("category_id")
    private Long categoryId;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("tags")
    private String tags;

    @TableField("type")
    private String type;

    @TableField("view_count")
    private Integer viewCount = 0;

    @TableField("like_count")
    private Integer likeCount = 0;

    @TableField("comment_count")
    private Integer commentCount = 0;

    @TableField("share_count")
    private Integer shareCount = 0;

    @TableField("status")
    private Integer status = 1;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

}