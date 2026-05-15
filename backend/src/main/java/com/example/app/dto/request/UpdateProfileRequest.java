package com.example.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {

    private String realName;

    private String school;

    private String major;

    private String grade;

    private String skills;

    private String githubUrl;

    private String blogUrl;

    private String bio;

    private String targetCompany;

}