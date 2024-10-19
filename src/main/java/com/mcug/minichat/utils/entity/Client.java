package com.mcug.minichat.utils.entity;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

@Data
public class Client {
    String token;
    String userId;
    WebSocketSession session;


    public Client() {
        this.token = "";
        this.userId = "";
        this.session = null;
    }

    public Client(String token, String userId, WebSocketSession session) {
        this.token = token;
        this.userId = userId;
        this.session = session;
    }
}
