package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.TimelineEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TimelineEventMapper extends BaseMapper<TimelineEvent> {

    List<TimelineEvent> findByUserId(@Param("userId") Long userId);

    List<TimelineEvent> findByUserIdAndType(@Param("userId") Long userId, @Param("eventType") String eventType);

    List<TimelineEvent> findByUserIdAndTimeRange(@Param("userId") Long userId, 
                                                  @Param("startTime") LocalDateTime startTime, 
                                                  @Param("endTime") LocalDateTime endTime);

}