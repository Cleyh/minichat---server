package com.mcug.minichat.clientManager.webSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcug.minichat.clientManager.ClientService;
import com.mcug.minichat.utils.GlobalName;
import com.mcug.minichat.utils.entity.ChatMessage;
import com.mcug.minichat.utils.entity.Client;
import com.mcug.minichat.utils.entity.ClientMessage;
import com.mcug.minichat.utils.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ClientSocketHandler extends TextWebSocketHandler {
    @Autowired
    private ClientService clientService;

    public String getTokenFromSession(WebSocketSession session) {
        return Objects.requireNonNull(session.getUri()).getQuery().substring("token=".length());
    }

    public String getUserIdFromSession(WebSocketSession session) {
        return TokenService.getIdFromToken(getTokenFromSession(session));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = Objects.requireNonNull(session.getUri()).getQuery();

        if (query == null || !query.contains("token=")) {
            session.close(CloseStatus.BAD_DATA);
            System.out.println("Client:" + session.getUri() + " try to connect with invalid token");
            return;
        }

        String clientToken = query.substring("token=".length());
        String userId = TokenService.getIdFromToken(clientToken);
        clientService.addClient(new Client(clientToken, userId, session));
        System.out.println("Client connected: " + userId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = TokenService.getIdFromToken(session.getUri().getQuery().substring("token=".length()));
        clientService.removeClient(userId);
        System.out.println("Client disconnected: " + userId);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message from client " + session.getId() + ": " + payload);

        // 将JSON字符串转换为Java对象
        ObjectMapper objectMapper = new ObjectMapper();
        ClientMessage receivedMessage = objectMapper.readValue(payload, ClientMessage.class);

        // 处理消息逻辑，例如转发给其他客户端
        if (receivedMessage.getType().equals(GlobalName.CHAT_TYPE_PRIVATE) || receivedMessage.getType().equals(GlobalName.CHAT_TYPE_PUBLIC)) {
            clientService.receiveChatFromClient(new ChatMessage()
                    .setSenderId(getUserIdFromSession(session))
                    .setReceiverId(receivedMessage.getReceiveUserId())
                    .setTimeStamp(new Date().getTime())
                    .setMessage(receivedMessage.getMessage()));
        }
    }
}
