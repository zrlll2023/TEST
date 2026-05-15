package com.example.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("email")
    private String email;

    @TableField("phone")
    private String phone;

    @TableField("password")
    private String password;

    @TableField("username")
    private String username;

    @TableField("avatar")
    private String avatar;

    @TableField("role")
    private String role = "USER";

    @TableField("status")
    private Integer status = 1;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("deleted")
    @TableLogic
    private Integer deleted = 0;

    @TableField(exist = false)
    private String token;

}