package com.example.app.service;

import com.example.app.entity.Comment;
import com.example.app.entity.Post;
import com.example.app.entity.PostCategory;

import java.util.List;

public interface CommunityService {

    List<PostCategory> getCategories();

    Post createPost(Long userId, Post post);

    Post getPost(Long postId);

    List<Post> getPosts(Long categoryId, String type, String keyword);

    List<Post> getHotPosts(Integer limit);

    List<Post> getRecentPosts(Integer limit);

    void likePost(Long postId);

    void sharePost(Long postId);

    Comment addComment(Long userId, Long postId, String content, Long parentId);

    List<Comment> getComments(Long postId);

    void likeComment(Long commentId);

}