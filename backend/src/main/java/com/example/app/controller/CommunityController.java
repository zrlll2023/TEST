package com.example.app.controller;

import com.example.app.common.Response;
import com.example.app.entity.Comment;
import com.example.app.entity.Post;
import com.example.app.entity.PostCategory;
import com.example.app.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping("/categories")
    public ResponseEntity<Response<List<PostCategory>>> getCategories() {
        List<PostCategory> categories = communityService.getCategories();
        return ResponseEntity.ok(Response.success(categories));
    }

    @PostMapping("/posts")
    public ResponseEntity<Response<Post>> createPost(
            @RequestParam Long userId,
            @RequestBody Post post) {
        Post created = communityService.createPost(userId, post);
        return ResponseEntity.ok(Response.success("发布成功", created));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Response<Post>> getPost(@PathVariable Long postId) {
        Post post = communityService.getPost(postId);
        return ResponseEntity.ok(Response.success(post));
    }

    @GetMapping("/posts")
    public ResponseEntity<Response<List<Post>>> getPosts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String keyword) {
        List<Post> posts = communityService.getPosts(categoryId, type, keyword);
        return ResponseEntity.ok(Response.success(posts));
    }

    @GetMapping("/posts/hot")
    public ResponseEntity<Response<List<Post>>> getHotPosts(@RequestParam(defaultValue = "10") Integer limit) {
        List<Post> posts = communityService.getHotPosts(limit);
        return ResponseEntity.ok(Response.success(posts));
    }

    @GetMapping("/posts/recent")
    public ResponseEntity<Response<List<Post>>> getRecentPosts(@RequestParam(defaultValue = "10") Integer limit) {
        List<Post> posts = communityService.getRecentPosts(limit);
        return ResponseEntity.ok(Response.success(posts));
    }

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<Response<Void>> likePost(@PathVariable Long postId) {
        communityService.likePost(postId);
        return ResponseEntity.ok(Response.success("点赞成功", null));
    }

    @PostMapping("/posts/{postId}/share")
    public ResponseEntity<Response<Void>> sharePost(@PathVariable Long postId) {
        communityService.sharePost(postId);
        return ResponseEntity.ok(Response.success("分享成功", null));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Response<List<Comment>>> getComments(@PathVariable Long postId) {
        List<Comment> comments = communityService.getComments(postId);
        return ResponseEntity.ok(Response.success(comments));
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Response<Comment>> addComment(
            @PathVariable Long postId,
            @RequestParam Long userId,
            @RequestBody Map<String, Object> request) {
        String content = (String) request.get("content");
        Long parentId = request.get("parentId") != null ? Long.parseLong(request.get("parentId").toString()) : null;
        
        Comment comment = communityService.addComment(userId, postId, content, parentId);
        return ResponseEntity.ok(Response.success("评论成功", comment));
    }

    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<Response<Void>> likeComment(@PathVariable Long commentId) {
        communityService.likeComment(commentId);
        return ResponseEntity.ok(Response.success("点赞成功", null));
    }

}