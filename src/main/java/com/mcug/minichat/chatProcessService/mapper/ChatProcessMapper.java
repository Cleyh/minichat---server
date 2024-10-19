package com.mcug.minichat.chatProcessService.mapper;

import com.mcug.minichat.utils.entity.ChatMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatProcessMapper {
    void insertPrivateChatMessage(ChatMessage chatMessage);

    void insertPublicChatMessage(ChatMessage chatMessage);
}
