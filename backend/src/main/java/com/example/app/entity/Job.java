package com.example.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("job")
public class Job {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("company_id")
    private Long companyId;

    @TableField("title")
    private String title;

    @TableField("location")
    private String location;

    @TableField("salary_min")
    private BigDecimal salaryMin;

    @TableField("salary_max")
    private BigDecimal salaryMax;

    @TableField("experience")
    private String experience;

    @TableField("education")
    private String education;

    @TableField("tags")
    private String tags;

    @TableField("description")
    private String description;

    @TableField("requirements")
    private String requirements;

    @TableField("deadline")
    private LocalDateTime deadline;

    @TableField("source")
    private String source;

    @TableField("view_count")
    private Integer viewCount = 0;

    @TableField("apply_count")
    private Integer applyCount = 0;

    @TableField("status")
    private Integer status = 1;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

}