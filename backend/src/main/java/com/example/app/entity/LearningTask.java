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
@TableName("learning_task")
public class LearningTask {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("path_id")
    private Long pathId;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("type")
    private String type;

    @TableField("resource_url")
    private String resourceUrl;

    @TableField("duration_minutes")
    private Integer durationMinutes;

    @TableField("order_num")
    private Integer orderNum;

    @TableField("created_at")
    private LocalDateTime createdAt;

}