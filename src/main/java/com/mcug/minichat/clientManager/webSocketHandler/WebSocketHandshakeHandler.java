package com.mcug.minichat.clientManager.webSocketHandler;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class WebSocketHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // 从请求中提取用户ID（假设用户ID作为参数传递）
        String userId = request.getHeaders().getFirst("userId");
        attributes.put("userId", userId);

        // 返回一个包含用户ID的Principal对象
        return () -> userId;
    }
}
