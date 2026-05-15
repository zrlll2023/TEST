package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.WrongQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WrongQuestionMapper extends BaseMapper<WrongQuestion> {

    List<WrongQuestion> findByUserId(@Param("userId") Long userId);

    List<WrongQuestion> findUnmasteredByUserId(@Param("userId") Long userId);

    WrongQuestion findByUserIdAndQuestionId(@Param("userId") Long userId, @Param("questionId") Long questionId);

    int incrementWrongCount(@Param("userId") Long userId, @Param("questionId") Long questionId);

    int incrementReviewCount(@Param("userId") Long userId, @Param("questionId") Long questionId);

    int updateMastered(@Param("userId") Long userId, @Param("questionId") Long questionId, @Param("mastered") Integer mastered);

}