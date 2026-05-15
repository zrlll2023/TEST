package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    List<Question> findByCategoryId(@Param("categoryId") Long categoryId);

    List<Question> findByDifficulty(@Param("difficulty") Integer difficulty);

    List<Question> findByType(@Param("questionType") String questionType);

    List<Question> search(@Param("keyword") String keyword);

    List<Question> findRandomByCategory(@Param("categoryId") Long categoryId, @Param("limit") Integer limit);

    List<Question> findWrongQuestionsByUserId(@Param("userId") Long userId);

    List<Question> findRecommendedQuestions(@Param("userId") Long userId, @Param("limit") Integer limit);

    int incrementViewCount(@Param("questionId") Long questionId);

    int incrementLikeCount(@Param("questionId") Long questionId);

}