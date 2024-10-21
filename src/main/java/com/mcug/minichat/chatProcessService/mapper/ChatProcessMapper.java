package com.mcug.minichat.chatProcessService.mapper;

import com.mcug.minichat.chatProcessService.dto.ChatMessageDTO;
import com.mcug.minichat.utils.entity.ChatMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatProcessMapper {
    void insertPrivateChatMessage(ChatMessage chatMessage);

    void insertPublicChatMessage(ChatMessage chatMessage);

    List<ChatMessageDTO> getUserLatestMessage(String userId);

    void refreshReceiveStatus(String userId);
}
