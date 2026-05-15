package com.example.app.service.impl;

import com.example.app.common.BusinessException;
import com.example.app.entity.LearningPath;
import com.example.app.entity.LearningTask;
import com.example.app.entity.TimelineEvent;
import com.example.app.entity.UserLearning;
import com.example.app.mapper.LearningPathMapper;
import com.example.app.mapper.LearningTaskMapper;
import com.example.app.mapper.TimelineEventMapper;
import com.example.app.mapper.UserLearningMapper;
import com.example.app.service.LearningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LearningServiceImpl implements LearningService {

    private final LearningPathMapper pathMapper;
    private final LearningTaskMapper taskMapper;
    private final UserLearningMapper userLearningMapper;
    private final TimelineEventMapper timelineMapper;

    @Override
    public List<LearningPath> getLearningPaths(String target, Integer difficulty) {
        if (target != null) {
            return pathMapper.findByTarget(target);
        }
        if (difficulty != null) {
            return pathMapper.findByDifficulty(difficulty);
        }
        return pathMapper.selectList(null);
    }

    @Override
    public LearningPath getLearningPath(Long pathId) {
        return pathMapper.selectById(pathId)
                .orElseThrow(() -> new BusinessException(404, "学习路径不存在"));
    }

    @Override
    public List<LearningTask> getTasksByPath(Long pathId) {
        return taskMapper.findByPathId(pathId);
    }

    @Override
    @Transactional
    public UserLearning startLearning(Long userId, Long pathId) {
        List<LearningTask> tasks = taskMapper.findByPathId(pathId);
        
        for (LearningTask task : tasks) {
            UserLearning existing = userLearningMapper.findByUserIdAndTaskId(userId, task.getId());
            if (existing == null) {
                UserLearning userLearning = new UserLearning();
                userLearning.setUserId(userId);
                userLearning.setPathId(pathId);
                userLearning.setTaskId(task.getId());
                userLearning.setStatus("PENDING");
                userLearning.setProgress(0);
                userLearning.setCreatedAt(LocalDateTime.now());
                userLearning.setUpdatedAt(LocalDateTime.now());
                userLearningMapper.insert(userLearning);
            }
        }
        
        UserLearning firstLearning = userLearningMapper.findByUserIdAndPathId(userId, pathId).get(0);
        return firstLearning;
    }

    @Override
    @Transactional
    public UserLearning completeTask(Long userId, Long taskId) {
        UserLearning userLearning = userLearningMapper.findByUserIdAndTaskId(userId, taskId);
        if (userLearning == null) {
            throw new BusinessException(404, "学习记录不存在");
        }
        
        userLearning.setStatus("COMPLETED");
        userLearning.setProgress(100);
        userLearning.setCompletedAt(LocalDateTime.now());
        userLearning.setUpdatedAt(LocalDateTime.now());
        userLearningMapper.updateById(userLearning);
        
        return userLearning;
    }

    @Override
    public Map<String, Object> getLearningProgress(Long userId) {
        Map<String, Object> progress = new HashMap<>();
        List<UserLearning> learnings = userLearningMapper.findByUserId(userId);
        
        int totalTasks = learnings.size();
        int completedTasks = (int) learnings.stream().filter(l -> "COMPLETED".equals(l.getStatus())).count();
        
        progress.put("totalTasks", totalTasks);
        progress.put("completedTasks", completedTasks);
        progress.put("progressPercent", totalTasks > 0 ? (completedTasks * 100) / totalTasks : 0);
        progress.put("learnings", learnings);
        
        return progress;
    }

    @Override
    public List<TimelineEvent> getTimeline(Long userId) {
        return timelineMapper.findByUserId(userId);
    }

    @Override
    @Transactional
    public TimelineEvent addTimelineEvent(Long userId, TimelineEvent event) {
        event.setUserId(userId);
        event.setCreatedAt(LocalDateTime.now());
        timelineMapper.insert(event);
        return event;
    }

    @Override
    @Transactional
    public void updateTimelineEvent(Long eventId, String status, String notes) {
        TimelineEvent event = timelineMapper.selectById(eventId)
                .orElseThrow(() -> new BusinessException(404, "事件不存在"));
        
        if (status != null) {
            event.setStatus(status);
        }
        if (notes != null) {
            event.setNotes(notes);
        }
        
        timelineMapper.updateById(event);
    }

    @Override
    public Map<String, Object> getDataDashboard(Long userId) {
        Map<String, Object> dashboard = new HashMap<>();
        
        int completedTasks = userLearningMapper.countCompletedByUserId(userId);
        int totalTasks = userLearningMapper.findByUserId(userId).size();
        
        dashboard.put("completedTasks", completedTasks);
        dashboard.put("totalTasks", totalTasks);
        dashboard.put("taskProgress", totalTasks > 0 ? (completedTasks * 100) / totalTasks : 0);
        
        dashboard.put("recentEvents", timelineMapper.findByUserId(userId).size());
        
        Map<String, Object> weeklyData = new HashMap<>();
        weeklyData.put("days", List.of("周一", "周二", "周三", "周四", "周五", "周六", "周日"));
        weeklyData.put("studyHours", List.of(2.5, 3.0, 1.5, 4.0, 2.0, 3.5, 2.0));
        dashboard.put("weeklyData", weeklyData);
        
        Map<String, Object> skillProgress = new HashMap<>();
        skillProgress.put("Java", 85);
        skillProgress.put("Spring Boot", 75);
        skillProgress.put("MySQL", 70);
        skillProgress.put("Redis", 60);
        dashboard.put("skillProgress", skillProgress);
        
        return dashboard;
    }

}