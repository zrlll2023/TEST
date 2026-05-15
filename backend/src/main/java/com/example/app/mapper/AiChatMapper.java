package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.AiChat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AiChatMapper extends BaseMapper<AiChat> {

    List<AiChat> findBySessionId(@Param("sessionId") String sessionId);

    List<AiChat> findByUserId(@Param("userId") Long userId);

    List<AiChat> findByUserIdAndType(@Param("userId") Long userId, @Param("chatType") String chatType);

    int deleteBySessionId(@Param("sessionId") String sessionId);

}