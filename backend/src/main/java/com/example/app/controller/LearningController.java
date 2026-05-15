package com.example.app.controller;

import com.example.app.common.Response;
import com.example.app.entity.LearningPath;
import com.example.app.entity.LearningTask;
import com.example.app.entity.TimelineEvent;
import com.example.app.entity.UserLearning;
import com.example.app.service.LearningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/learning")
@RequiredArgsConstructor
public class LearningController {

    private final LearningService learningService;

    @GetMapping("/paths")
    public ResponseEntity<Response<List<LearningPath>>> getLearningPaths(
            @RequestParam(required = false) String target,
            @RequestParam(required = false) Integer difficulty) {
        List<LearningPath> paths = learningService.getLearningPaths(target, difficulty);
        return ResponseEntity.ok(Response.success(paths));
    }

    @GetMapping("/paths/{pathId}")
    public ResponseEntity<Response<LearningPath>> getLearningPath(@PathVariable Long pathId) {
        LearningPath path = learningService.getLearningPath(pathId);
        return ResponseEntity.ok(Response.success(path));
    }

    @GetMapping("/paths/{pathId}/tasks")
    public ResponseEntity<Response<List<LearningTask>>> getTasksByPath(@PathVariable Long pathId) {
        List<LearningTask> tasks = learningService.getTasksByPath(pathId);
        return ResponseEntity.ok(Response.success(tasks));
    }

    @PostMapping("/{userId}/start")
    public ResponseEntity<Response<UserLearning>> startLearning(
            @PathVariable Long userId,
            @RequestParam Long pathId) {
        UserLearning learning = learningService.startLearning(userId, pathId);
        return ResponseEntity.ok(Response.success("开始学习", learning));
    }

    @PostMapping("/{userId}/tasks/{taskId}/complete")
    public ResponseEntity<Response<UserLearning>> completeTask(
            @PathVariable Long userId,
            @PathVariable Long taskId) {
        UserLearning learning = learningService.completeTask(userId, taskId);
        return ResponseEntity.ok(Response.success("任务完成", learning));
    }

    @GetMapping("/{userId}/progress")
    public ResponseEntity<Response<Map<String, Object>>> getLearningProgress(@PathVariable Long userId) {
        Map<String, Object> progress = learningService.getLearningProgress(userId);
        return ResponseEntity.ok(Response.success(progress));
    }

    @GetMapping("/{userId}/timeline")
    public ResponseEntity<Response<List<TimelineEvent>>> getTimeline(@PathVariable Long userId) {
        List<TimelineEvent> events = learningService.getTimeline(userId);
        return ResponseEntity.ok(Response.success(events));
    }

    @PostMapping("/{userId}/timeline")
    public ResponseEntity<Response<TimelineEvent>> addTimelineEvent(
            @PathVariable Long userId,
            @RequestBody TimelineEvent event) {
        TimelineEvent created = learningService.addTimelineEvent(userId, event);
        return ResponseEntity.ok(Response.success("事件添加成功", created));
    }

    @PutMapping("/timeline/{eventId}")
    public ResponseEntity<Response<Void>> updateTimelineEvent(
            @PathVariable Long eventId,
            @RequestBody Map<String, String> request) {
        String status = request.get("status");
        String notes = request.get("notes");
        learningService.updateTimelineEvent(eventId, status, notes);
        return ResponseEntity.ok(Response.success("更新成功", null));
    }

    @GetMapping("/{userId}/dashboard")
    public ResponseEntity<Response<Map<String, Object>>> getDataDashboard(@PathVariable Long userId) {
        Map<String, Object> dashboard = learningService.getDataDashboard(userId);
        return ResponseEntity.ok(Response.success(dashboard));
    }

}