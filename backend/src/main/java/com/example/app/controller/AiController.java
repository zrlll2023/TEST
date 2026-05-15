package com.example.app.controller;

import com.example.app.common.Response;
import com.example.app.entity.AiChat;
import com.example.app.entity.AiInterview;
import com.example.app.entity.AiResumeAnalysis;
import com.example.app.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/resume/analyze")
    public ResponseEntity<Response<AiResumeAnalysis>> analyzeResume(
            @RequestParam Long userId,
            @RequestBody Map<String, String> request) {
        String resumeContent = request.get("content");
        AiResumeAnalysis analysis = aiService.analyzeResume(userId, resumeContent);
        return ResponseEntity.ok(Response.success(analysis));
    }

    @PostMapping("/interview/start")
    public ResponseEntity<Response<AiInterview>> startInterview(
            @RequestParam Long userId,
            @RequestBody Map<String, String> request) {
        String targetPosition = request.get("targetPosition");
        String targetCompany = request.get("targetCompany");
        AiInterview interview = aiService.startInterview(userId, targetPosition, targetCompany);
        return ResponseEntity.ok(Response.success("面试已开始", interview));
    }

    @GetMapping("/interview/{interviewId}/question")
    public ResponseEntity<Response<Map<String, Object>>> getNextQuestion(@PathVariable Long interviewId) {
        Map<String, Object> question = aiService.getNextQuestion(interviewId);
        return ResponseEntity.ok(Response.success(question));
    }

    @PostMapping("/interview/{interviewId}/answer")
    public ResponseEntity<Response<Map<String, Object>>> submitAnswer(
            @PathVariable Long interviewId,
            @RequestBody Map<String, String> request) {
        String answer = request.get("answer");
        Map<String, Object> result = aiService.submitAnswer(interviewId, answer);
        return ResponseEntity.ok(Response.success(result));
    }

    @PostMapping("/interview/{interviewId}/finish")
    public ResponseEntity<Response<AiInterview>> finishInterview(@PathVariable Long interviewId) {
        AiInterview interview = aiService.finishInterview(interviewId);
        return ResponseEntity.ok(Response.success("面试已结束", interview));
    }

    @GetMapping("/interview/history/{userId}")
    public ResponseEntity<Response<List<AiInterview>>> getInterviewHistory(@PathVariable Long userId) {
        List<AiInterview> history = aiService.getInterviewHistory(userId);
        return ResponseEntity.ok(Response.success(history));
    }

    @PostMapping("/chat")
    public ResponseEntity<Response<String>> chatWithAi(
            @RequestParam Long userId,
            @RequestParam(required = false) String sessionId,
            @RequestParam(defaultValue = "general") String chatType,
            @RequestBody Map<String, String> request) {
        String message = request.get("message");
        String response = aiService.chatWithAi(userId, sessionId, message, chatType);
        return ResponseEntity.ok(Response.success(response));
    }

    @GetMapping("/chat/history")
    public ResponseEntity<Response<List<AiChat>>> getChatHistory(
            @RequestParam Long userId,
            @RequestParam String sessionId) {
        List<AiChat> history = aiService.getChatHistory(userId, sessionId);
        return ResponseEntity.ok(Response.success(history));
    }

    @PostMapping("/code/review")
    public ResponseEntity<Response<String>> reviewCode(
            @RequestParam Long userId,
            @RequestBody Map<String, String> request) {
        String code = request.get("code");
        String language = request.get("language");
        String review = aiService.reviewCode(userId, code, language);
        return ResponseEntity.ok(Response.success(review));
    }

    @PostMapping("/career/advice")
    public ResponseEntity<Response<String>> getCareerAdvice(
            @RequestParam Long userId,
            @RequestBody Map<String, String> request) {
        String question = request.get("question");
        String advice = aiService.getCareerAdvice(userId, question);
        return ResponseEntity.ok(Response.success(advice));
    }

}