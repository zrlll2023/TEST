package com.example.app.service.impl;

import com.example.app.common.BusinessException;
import com.example.app.dto.request.SubmitAnswerRequest;
import com.example.app.entity.Question;
import com.example.app.entity.QuestionCategory;
import com.example.app.entity.UserQuestion;
import com.example.app.entity.WrongQuestion;
import com.example.app.mapper.QuestionCategoryMapper;
import com.example.app.mapper.QuestionMapper;
import com.example.app.mapper.UserQuestionMapper;
import com.example.app.mapper.WrongQuestionMapper;
import com.example.app.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;
    private final QuestionCategoryMapper categoryMapper;
    private final UserQuestionMapper userQuestionMapper;
    private final WrongQuestionMapper wrongQuestionMapper;

    @Override
    public List<QuestionCategory> getCategories() {
        return categoryMapper.findAllWithCount();
    }

    @Override
    public List<Question> getQuestions(Long categoryId, Integer difficulty, String type) {
        if (categoryId != null) {
            return questionMapper.findByCategoryId(categoryId);
        }
        if (difficulty != null) {
            return questionMapper.findByDifficulty(difficulty);
        }
        if (type != null) {
            return questionMapper.findByType(type);
        }
        return questionMapper.selectList(null);
    }

    @Override
    public Question getQuestion(Long questionId) {
        Question question = questionMapper.selectById(questionId)
                .orElseThrow(() -> new BusinessException(404, "题目不存在"));
        questionMapper.incrementViewCount(questionId);
        return question;
    }

    @Override
    @Transactional
    public Question submitAnswer(Long userId, SubmitAnswerRequest request) {
        Question question = questionMapper.selectById(request.getQuestionId())
                .orElseThrow(() -> new BusinessException(404, "题目不存在"));

        int isCorrect = 0;
        if (question.getAnswer() != null && question.getAnswer().equals(request.getUserAnswer())) {
            isCorrect = 1;
        }

        UserQuestion existing = userQuestionMapper.findByUserIdAndQuestionId(userId, request.getQuestionId());
        if (existing != null) {
            existing.setUserAnswer(request.getUserAnswer());
            existing.setIsCorrect(isCorrect);
            existing.setTimeSpent(request.getTimeSpent());
            existing.setAttemptCount(existing.getAttemptCount() + 1);
            existing.setUpdatedAt(LocalDateTime.now());
            userQuestionMapper.updateById(existing);
        } else {
            UserQuestion userQuestion = new UserQuestion();
            userQuestion.setUserId(userId);
            userQuestion.setQuestionId(request.getQuestionId());
            userQuestion.setUserAnswer(request.getUserAnswer());
            userQuestion.setIsCorrect(isCorrect);
            userQuestion.setTimeSpent(request.getTimeSpent());
            userQuestion.setCreatedAt(LocalDateTime.now());
            userQuestion.setUpdatedAt(LocalDateTime.now());
            userQuestionMapper.insert(userQuestion);
        }

        if (isCorrect == 0) {
            WrongQuestion wrong = wrongQuestionMapper.findByUserIdAndQuestionId(userId, request.getQuestionId());
            if (wrong != null) {
                wrongQuestionMapper.incrementWrongCount(userId, request.getQuestionId());
                wrong.setLastWrongTime(LocalDateTime.now());
                wrong.setUpdatedAt(LocalDateTime.now());
            } else {
                WrongQuestion newWrong = new WrongQuestion();
                newWrong.setUserId(userId);
                newWrong.setQuestionId(request.getQuestionId());
                newWrong.setLastWrongTime(LocalDateTime.now());
                newWrong.setCreatedAt(LocalDateTime.now());
                newWrong.setUpdatedAt(LocalDateTime.now());
                wrongQuestionMapper.insert(newWrong);
            }
        } else {
            WrongQuestion wrong = wrongQuestionMapper.findByUserIdAndQuestionId(userId, request.getQuestionId());
            if (wrong != null) {
                wrongQuestionMapper.updateMastered(userId, request.getQuestionId(), 1);
            }
        }

        return question;
    }

    @Override
    public List<Question> getWrongQuestions(Long userId) {
        return questionMapper.findWrongQuestionsByUserId(userId);
    }

    @Override
    public List<Question> getRecommendedQuestions(Long userId, Integer limit) {
        return questionMapper.findRecommendedQuestions(userId, limit);
    }

    @Override
    public List<Question> getRandomQuestions(Long categoryId, Integer limit) {
        return questionMapper.findRandomByCategory(categoryId, limit);
    }

    @Override
    public Map<String, Object> getUserStats(Long userId) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalAnswered", userQuestionMapper.getTotalCountByUserId(userId));
        stats.put("correctCount", userQuestionMapper.getCorrectCountByUserId(userId));
        stats.put("wrongCount", wrongQuestionMapper.findByUserId(userId).size());
        return stats;
    }

    @Override
    @Transactional
    public void markWrongQuestionMastered(Long userId, Long questionId) {
        wrongQuestionMapper.updateMastered(userId, questionId, 1);
    }

}