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
@TableName("timeline_event")
public class TimelineEvent {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("event_type")
    private String eventType;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("related_id")
    private Long relatedId;

    @TableField("event_time")
    private LocalDateTime eventTime;

    @TableField("status")
    private String status;

    @TableField("notes")
    private String notes;

    @TableField("created_at")
    private LocalDateTime createdAt;

}