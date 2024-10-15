package com.mcug.minichat.clientManager.config;

import com.mcug.minichat.clientManager.webSocketHandler.ClientSocketHandler;
import com.mcug.minichat.clientManager.webSocketHandler.WebSocketHandshakeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ClientSocketHandler clientSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(clientSocketHandler, "/ws/chat")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new WebSocketHandshakeHandler());
    }
}
