package com.example.app.service;

import com.example.app.entity.AiChat;
import com.example.app.entity.AiInterview;
import com.example.app.entity.AiResumeAnalysis;

import java.util.List;
import java.util.Map;

public interface AiService {

    AiResumeAnalysis analyzeResume(Long userId, String resumeContent);

    AiInterview startInterview(Long userId, String targetPosition, String targetCompany);

    Map<String, Object> getNextQuestion(Long interviewId);

    Map<String, Object> submitAnswer(Long interviewId, String answer);

    AiInterview finishInterview(Long interviewId);

    List<AiInterview> getInterviewHistory(Long userId);

    String chatWithAi(Long userId, String sessionId, String message, String chatType);

    List<AiChat> getChatHistory(Long userId, String sessionId);

    String reviewCode(Long userId, String code, String language);

    String getCareerAdvice(Long userId, String question);

}