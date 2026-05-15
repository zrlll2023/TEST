package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> findByPostId(@Param("postId") Long postId);

    List<Comment> findByParentId(@Param("parentId") Long parentId);

    List<Comment> findByUserId(@Param("userId") Long userId);

    int incrementLikeCount(@Param("commentId") Long commentId);

}