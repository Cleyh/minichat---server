package com.mcug.minichat.clientManager;

import com.mcug.minichat.clientManager.event.ChatEventService;
import com.mcug.minichat.clientManager.event.PublicChatEventService;
import com.mcug.minichat.utils.GlobalName;
import com.mcug.minichat.utils.entity.ChatMessage;
import com.mcug.minichat.utils.entity.Client;
import com.mcug.minichat.clientManager.event.LoginEventService;
import com.mcug.minichat.utils.entity.ClientMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Service
public class ClientService {

    @Autowired
    LoginEventService loginEventService;
    @Autowired
    ChatEventService chatEventService;
    @Autowired
    PublicChatEventService publicChatEventService;

    private static final Set<String> readyClient = new HashSet<>();
    private static final Map<String, Client> clientPools = new ConcurrentHashMap<>();

    public static void addReadyClient(String clientToken) {
        readyClient.add(clientToken);
    }

    public void addClient(Client client) throws IOException {
//        // 是否已登录的客户端
//        if (!readyClient.contains(client.getToken())) {
//            client.getSession().close();
//            throw new IOException("该用户未登录");
//        }
        // 关闭重复的连接
        if (clientPools.containsKey(client.getUserId())) {
            Client oldClient = clientPools.get(client.getUserId());
            oldClient.getSession().close();
            clientPools.remove(client.getUserId());
        }
        clientPools.put(client.getUserId(), client);
        readyClient.remove(client.getToken());
    }

    public void removeClient(String token) {
        clientPools.remove(token);
    }

    public void receiveChatFromClient(ChatMessage chatMessage) throws IOException {
        // 在线的情况
        if (clientPools.containsKey(chatMessage.getReceiverId())) {
            chatEventService.readySendChatMessage(chatMessage, GlobalName.ONLINE);
        } else if (chatMessage.getReceiverId().equals(GlobalName.CHAT_PUBLIC_RECEIVER)) {
            publicChatEventService.readySendPublicChatMessage(chatMessage);
        } else {
            chatEventService.readySendChatMessage(chatMessage, GlobalName.OFFLINE);
        }
    }

    public static void sendChatToClient(ChatMessage chatMessage) throws IOException {
        String receiverId = chatMessage.getReceiverId();
        if (!clientPools.containsKey(receiverId)) {
            throw new IOException("用户不在线");
        }
        Client client = clientPools.get(receiverId);
        ClientMessage clientMessage = new ClientMessage()
                .setSenderUserId(chatMessage.getSenderId())
                .setReceiveUserId(receiverId)
                .setType(GlobalName.CHAT_TYPE_PRIVATE)
                .setMessage(chatMessage.getMessage());
        client.getSession().sendMessage(new TextMessage(clientMessage.toJson()));
    }

    public static void sendPublicChatToClient(ChatMessage chatMessage) throws IOException {
        for (Client client : clientPools.values()) {
            ClientMessage clientMessage = new ClientMessage()
                    .setSenderUserId(chatMessage.getSenderId())
                    .setReceiveUserId(GlobalName.CHAT_PUBLIC_RECEIVER)
                    .setType(GlobalName.CHAT_TYPE_PUBLIC)
                    .setMessage(chatMessage.getMessage())
                    .setTimeStamp(chatMessage.getTimeStamp());
            client.getSession().sendMessage(new TextMessage(clientMessage.toJson()));
        }
    }
}
