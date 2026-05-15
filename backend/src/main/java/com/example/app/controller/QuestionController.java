package com.example.app.controller;

import com.example.app.common.Response;
import com.example.app.dto.request.SubmitAnswerRequest;
import com.example.app.entity.Question;
import com.example.app.entity.QuestionCategory;
import com.example.app.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/categories")
    public ResponseEntity<Response<List<QuestionCategory>>> getCategories() {
        List<QuestionCategory> categories = questionService.getCategories();
        return ResponseEntity.ok(Response.success(categories));
    }

    @GetMapping
    public ResponseEntity<Response<List<Question>>> getQuestions(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Integer difficulty,
            @RequestParam(required = false) String type) {
        List<Question> questions = questionService.getQuestions(categoryId, difficulty, type);
        return ResponseEntity.ok(Response.success(questions));
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Response<Question>> getQuestion(@PathVariable Long questionId) {
        Question question = questionService.getQuestion(questionId);
        return ResponseEntity.ok(Response.success(question));
    }

    @PostMapping("/{userId}/submit")
    public ResponseEntity<Response<Question>> submitAnswer(
            @PathVariable Long userId,
            @Valid @RequestBody SubmitAnswerRequest request) {
        Question question = questionService.submitAnswer(userId, request);
        return ResponseEntity.ok(Response.success(question));
    }

    @GetMapping("/{userId}/wrong")
    public ResponseEntity<Response<List<Question>>> getWrongQuestions(@PathVariable Long userId) {
        List<Question> questions = questionService.getWrongQuestions(userId);
        return ResponseEntity.ok(Response.success(questions));
    }

    @GetMapping("/{userId}/recommend")
    public ResponseEntity<Response<List<Question>>> getRecommendedQuestions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "10") Integer limit) {
        List<Question> questions = questionService.getRecommendedQuestions(userId, limit);
        return ResponseEntity.ok(Response.success(questions));
    }

    @GetMapping("/random")
    public ResponseEntity<Response<List<Question>>> getRandomQuestions(
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "10") Integer limit) {
        List<Question> questions = questionService.getRandomQuestions(categoryId, limit);
        return ResponseEntity.ok(Response.success(questions));
    }

    @GetMapping("/{userId}/stats")
    public ResponseEntity<Response<Map<String, Object>>> getUserStats(@PathVariable Long userId) {
        Map<String, Object> stats = questionService.getUserStats(userId);
        return ResponseEntity.ok(Response.success(stats));
    }

    @PutMapping("/{userId}/wrong/{questionId}/master")
    public ResponseEntity<Response<Void>> markWrongQuestionMastered(
            @PathVariable Long userId,
            @PathVariable Long questionId) {
        questionService.markWrongQuestionMastered(userId, questionId);
        return ResponseEntity.ok(Response.success("已标记为掌握", null));
    }

}