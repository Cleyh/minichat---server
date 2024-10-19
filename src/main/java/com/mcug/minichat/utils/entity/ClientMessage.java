package com.mcug.minichat.utils.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/*
* 用于 WebSocket 的消息体结构
* */
@Data
public class ClientMessage {
    private String type;
    private Long timeStamp;
    private String senderUserId;
    private String receiveUserId;
    private String message;

    public ClientMessage() {
    }

    public ClientMessage setType(String type) {
        this.type = type;
        return this;
    }

    public ClientMessage setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public ClientMessage setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }

    public ClientMessage setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
        return this;
    }

    public ClientMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    // 序列化
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

}
