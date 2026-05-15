package com.example.app.service;

import com.example.app.dto.request.SubmitAnswerRequest;
import com.example.app.entity.Question;
import com.example.app.entity.QuestionCategory;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    List<QuestionCategory> getCategories();

    List<Question> getQuestions(Long categoryId, Integer difficulty, String type);

    Question getQuestion(Long questionId);

    Question submitAnswer(Long userId, SubmitAnswerRequest request);

    List<Question> getWrongQuestions(Long userId);

    List<Question> getRecommendedQuestions(Long userId, Integer limit);

    List<Question> getRandomQuestions(Long categoryId, Integer limit);

    Map<String, Object> getUserStats(Long userId);

    void markWrongQuestionMastered(Long userId, Long questionId);

}