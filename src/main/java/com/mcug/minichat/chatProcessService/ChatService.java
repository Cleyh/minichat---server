package com.mcug.minichat.chatProcessService;

import com.mcug.minichat.chatProcessService.dto.SendDTO;
import com.mcug.minichat.chatProcessService.mapper.ChatProcessMapper;
import com.mcug.minichat.utils.entity.ChatMessage;
import com.mcug.minichat.chatProcessService.event.ChatProcessEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private ChatProcessMapper chatProcessMapper;

    public void savePrivateChat(ChatMessage chatMessage) {
        chatProcessMapper.insertPrivateChatMessage(chatMessage);
    }

    public void savePublicChat(ChatMessage chatMessage) {
        chatProcessMapper.insertPublicChatMessage(chatMessage);
    }
}
