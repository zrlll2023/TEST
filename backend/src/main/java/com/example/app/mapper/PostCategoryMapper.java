package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.PostCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostCategoryMapper extends BaseMapper<PostCategory> {

    List<PostCategory> findAllOrdered();

}