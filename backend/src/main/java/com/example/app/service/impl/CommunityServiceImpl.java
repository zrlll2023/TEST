package com.example.app.service.impl;

import com.example.app.common.BusinessException;
import com.example.app.entity.Comment;
import com.example.app.entity.Post;
import com.example.app.entity.PostCategory;
import com.example.app.mapper.CommentMapper;
import com.example.app.mapper.PostCategoryMapper;
import com.example.app.mapper.PostMapper;
import com.example.app.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final PostCategoryMapper categoryMapper;

    @Override
    public List<PostCategory> getCategories() {
        return categoryMapper.findAllOrdered();
    }

    @Override
    @Transactional
    public Post createPost(Long userId, Post post) {
        post.setUserId(userId);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        postMapper.insert(post);
        return post;
    }

    @Override
    public Post getPost(Long postId) {
        Post post = postMapper.selectById(postId)
                .orElseThrow(() -> new BusinessException(404, "帖子不存在"));
        postMapper.incrementViewCount(postId);
        return post;
    }

    @Override
    public List<Post> getPosts(Long categoryId, String type, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return postMapper.search(keyword);
        }
        if (categoryId != null) {
            return postMapper.findByCategoryId(categoryId);
        }
        if (type != null) {
            return postMapper.findByType(type);
        }
        return postMapper.selectList(null);
    }

    @Override
    public List<Post> getHotPosts(Integer limit) {
        return postMapper.findHotPosts(limit);
    }

    @Override
    public List<Post> getRecentPosts(Integer limit) {
        return postMapper.findRecentPosts(limit);
    }

    @Override
    @Transactional
    public void likePost(Long postId) {
        postMapper.incrementLikeCount(postId);
    }

    @Override
    @Transactional
    public void sharePost(Long postId) {
        postMapper.incrementShareCount(postId);
    }

    @Override
    @Transactional
    public Comment addComment(Long userId, Long postId, String content, Long parentId) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setPostId(postId);
        comment.setContent(content);
        comment.setParentId(parentId);
        comment.setCreatedAt(LocalDateTime.now());
        commentMapper.insert(comment);
        
        postMapper.incrementCommentCount(postId);
        
        return comment;
    }

    @Override
    public List<Comment> getComments(Long postId) {
        return commentMapper.findByPostId(postId);
    }

    @Override
    @Transactional
    public void likeComment(Long commentId) {
        commentMapper.incrementLikeCount(commentId);
    }

}