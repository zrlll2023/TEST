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
@TableName("learning_path")
public class LearningPath {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("target")
    private String target;

    @TableField("description")
    private String description;

    @TableField("duration_days")
    private Integer durationDays;

    @TableField("difficulty")
    private Integer difficulty;

    @TableField("icon")
    private String icon;

    @TableField("created_at")
    private LocalDateTime createdAt;

}