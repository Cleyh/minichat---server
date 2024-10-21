package com.mcug.minichat.chatProcessService;

import com.mcug.minichat.chatProcessService.dto.ChatMessageDTO;
import com.mcug.minichat.chatProcessService.dto.SendDTO;
import com.mcug.minichat.chatProcessService.mapper.ChatProcessMapper;
import com.mcug.minichat.utils.entity.ChatMessage;
import com.mcug.minichat.chatProcessService.event.ChatProcessEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<ChatMessage> getUserLatestMessage(String userId) {
        List<ChatMessageDTO> chatList = chatProcessMapper.getUserLatestMessage(userId);
        List<ChatMessage> chatMessageList = (List<ChatMessage>) chatList.stream().map(chatMessageDTO -> new ChatMessage()
                .setSenderId(chatMessageDTO.getSenderId())
                .setReceiverId(chatMessageDTO.getReceiverId())
                .setMessage(chatMessageDTO.getMessage())
                .setTimeStamp(chatMessageDTO.getTimeStamp()));

        chatProcessMapper.refreshReceiveStatus(userId);

        return chatMessageList;
    }
}
