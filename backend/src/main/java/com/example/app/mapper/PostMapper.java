package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    List<Post> findByCategoryId(@Param("categoryId") Long categoryId);

    List<Post> findByUserId(@Param("userId") Long userId);

    List<Post> findByType(@Param("type") String type);

    List<Post> search(@Param("keyword") String keyword);

    List<Post> findHotPosts(@Param("limit") Integer limit);

    List<Post> findRecentPosts(@Param("limit") Integer limit);

    int incrementViewCount(@Param("postId") Long postId);

    int incrementLikeCount(@Param("postId") Long postId);

    int incrementCommentCount(@Param("postId") Long postId);

    int incrementShareCount(@Param("postId") Long postId);

}