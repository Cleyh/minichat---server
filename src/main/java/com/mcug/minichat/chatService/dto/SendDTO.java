package com.mcug.minichat.chatService.dto;

import lombok.Data;

@Data
public class SendDTO {
    String receiverId;
    String message;
}
