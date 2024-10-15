package com.mcug.minichat.chatService;

import com.mcug.minichat.chatService.dto.SendDTO;
import com.mcug.minichat.chatService.entity.ChatMessage;
import com.mcug.minichat.chatService.event.ChatEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderService {
    @Autowired
    private ChatEventService chatEventService;

    public void sendChatMessage(String userId, SendDTO message) {
        ChatMessage chatMessage = new ChatMessage().setSenderId(userId).setReceiverId(message.getReceiverId()).setMessage(message.getMessage());
        chatEventService.redirectionChat(chatMessage);
    }
}
