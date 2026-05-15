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
@TableName("company_review")
public class CompanyReview {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("company_id")
    private Long companyId;

    @TableField("company_name")
    private String companyName;

    @TableField("rating")
    private Integer rating;

    @TableField("pros")
    private String pros;

    @TableField("cons")
    private String cons;

    @TableField("advice")
    private String advice;

    @TableField("created_at")
    private LocalDateTime createdAt;

}