package com.mcug.minichat.clientManager.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcug.minichat.clientManager.ClientService;
import com.mcug.minichat.globalConfig.rabbiMQ.RabbitMQConfig;
import com.mcug.minichat.utils.EventName;
import com.mcug.minichat.utils.GlobalName;
import com.mcug.minichat.utils.entity.ChatMessage;
import com.mcug.minichat.utils.entity.EventMessage;
import com.mcug.minichat.utils.globeMessage.EventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ChatEventService extends EventService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /*
     * 监听私人聊天队列
     * */
    @RabbitListener(queues = RabbitMQConfig.SenderQueue)
    public void chatListener(EventMessage eventMessage) throws IOException {
        receiveChatMessage(eventMessage);
    }

    public void receiveChatMessage(EventMessage eventMessage) throws IOException {
        // 忽略离线消息
        if (!eventMessage.getEventName().equals(EventName.CHAT_EVENT) || eventMessage.getMessage().equals(GlobalName.OFFLINE)) {
            return;
        }
        ChatMessage chatMessage = objectMapper.convertValue(eventMessage.getData(), ChatMessage.class);
        ClientService.sendChatToClient(chatMessage);
    }

    public void readySendChatMessage(ChatMessage chatMessage, String status) {
        sendMessage(RabbitMQConfig.PrivateChatExchange,
                "",
                new EventMessage().setEventName(EventName.CHAT_EVENT).setData(chatMessage).setMessage(status));
    }


}
