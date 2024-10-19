package com.mcug.minichat.utils.entity;

import lombok.Data;

@Data
public class ChatMessage {
    String senderId;
    String receiverId;
    String message;
    long timeStamp;

    public ChatMessage setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public ChatMessage setReceiverId(String receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    public ChatMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public ChatMessage setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }
}
