package com.mcug.minichat.chatProcessService.dto;

import lombok.Data;

@Data
public class SendDTO {
    String receiverId;
    String message;
}
