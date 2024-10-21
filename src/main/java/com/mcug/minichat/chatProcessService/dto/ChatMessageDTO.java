package com.mcug.minichat.chatProcessService.dto;

import lombok.Data;

@Data
public class ChatMessageDTO {
    long id;
    long timeStamp;
    String senderId;
    String receiverId;
    String message;
    boolean received;
}
