package com.example.app.service;

import com.example.app.entity.LearningPath;
import com.example.app.entity.LearningTask;
import com.example.app.entity.TimelineEvent;
import com.example.app.entity.UserLearning;

import java.util.List;
import java.util.Map;

public interface LearningService {

    List<LearningPath> getLearningPaths(String target, Integer difficulty);

    LearningPath getLearningPath(Long pathId);

    List<LearningTask> getTasksByPath(Long pathId);

    UserLearning startLearning(Long userId, Long pathId);

    UserLearning completeTask(Long userId, Long taskId);

    Map<String, Object> getLearningProgress(Long userId);

    List<TimelineEvent> getTimeline(Long userId);

    TimelineEvent addTimelineEvent(Long userId, TimelineEvent event);

    void updateTimelineEvent(Long eventId, String status, String notes);

    Map<String, Object> getDataDashboard(Long userId);

}