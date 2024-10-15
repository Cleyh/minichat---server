package com.mcug.minichat.clientManager;

import com.mcug.minichat.clientManager.entity.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Service
public class ClientService {
    private static final Set<String> readyClient = new HashSet<>();
    private final Map<String, Client> clientPools = new ConcurrentHashMap<>();

    public static void addReadyClient(String clientToken) {
        readyClient.add(clientToken);
    }

    public void addClient(Client client) throws IOException {
        // 是否已登录的客户端
        if (!readyClient.contains(client.getToken())) {
            client.getSession().close();
            throw new IOException("该用户未登录");
        }
        // 关闭重复的连接
        if (clientPools.containsKey(client.getUserId())) {
            Client oldClient = clientPools.get(client.getUserId());
            oldClient.getSession().close();
            clientPools.remove(client.getUserId());
        }
        clientPools.put(client.getUserId(), client);
        readyClient.remove(client.getToken());
    }

    public void removeClient(String userId) {
        clientPools.remove(userId);
    }
}
