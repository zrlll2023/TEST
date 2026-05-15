package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.UserLearning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserLearningMapper extends BaseMapper<UserLearning> {

    List<UserLearning> findByUserId(@Param("userId") Long userId);

    List<UserLearning> findByUserIdAndPathId(@Param("userId") Long userId, @Param("pathId") Long pathId);

    UserLearning findByUserIdAndTaskId(@Param("userId") Long userId, @Param("taskId") Long taskId);

    int countCompletedByUserId(@Param("userId") Long userId);

    int countCompletedByPathId(@Param("userId") Long userId, @Param("pathId") Long pathId);

}