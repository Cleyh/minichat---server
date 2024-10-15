package com.mcug.minichat.clientManager.webSocketHandler;
import com.mcug.minichat.clientManager.ClientService;
import com.mcug.minichat.clientManager.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

@Component
public class ClientSocketHandler extends TextWebSocketHandler{
    @Autowired
    private ClientService clientService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String clientToken = session.getHandshakeHeaders().get("token").get(0);
        String userId = session.getId();
        clientService.addClient(new Client(clientToken, userId, session));
        System.out.println("Client connected: " + userId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

    }

    @Override
    public void handleTextMessage(WebSocketSession session, org.springframework.web.socket.TextMessage message) throws Exception {

    }
}
