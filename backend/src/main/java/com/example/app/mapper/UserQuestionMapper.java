package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.UserQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserQuestionMapper extends BaseMapper<UserQuestion> {

    List<UserQuestion> findByUserId(@Param("userId") Long userId);

    List<UserQuestion> findByUserIdAndCategory(@Param("userId") Long userId, @Param("categoryId") Long categoryId);

    UserQuestion findByUserIdAndQuestionId(@Param("userId") Long userId, @Param("questionId") Long questionId);

    int getCorrectCountByUserId(@Param("userId") Long userId);

    int getTotalCountByUserId(@Param("userId") Long userId);

}