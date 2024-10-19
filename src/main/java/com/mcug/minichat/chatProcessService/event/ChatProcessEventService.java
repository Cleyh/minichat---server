package com.mcug.minichat.chatProcessService.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcug.minichat.chatProcessService.ChatService;
import com.mcug.minichat.utils.EventName;
import com.mcug.minichat.utils.GlobalName;
import com.mcug.minichat.utils.entity.ChatMessage;
import com.mcug.minichat.globalConfig.rabbiMQ.RabbitMQConfig;
import com.mcug.minichat.utils.entity.EventMessage;
import com.mcug.minichat.utils.globeMessage.EventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ChatProcessEventService extends EventService {
    @Autowired
    private ChatService chatService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = RabbitMQConfig.ChatProcessQueue)
    public void chatListener(EventMessage eventMessage) {
        String eventName = eventMessage.getEventName();
        String status = eventMessage.getMessage();

        if (eventName.equals(EventName.CHAT_EVENT) && status.equals(GlobalName.OFFLINE)) {
            receiveChatMessage(eventMessage);
        } else if (eventName.equals(EventName.PUBLIC_CHAT_EVENT)) {
            receivePublicMessage(eventMessage);
        }
    }

    private void receivePublicMessage(EventMessage eventMessage) {
        ChatMessage chatMessage = objectMapper.convertValue(eventMessage.getData(), ChatMessage.class);
        chatService.savePublicChat(chatMessage);
    }

    public void receiveChatMessage(EventMessage eventMessage) {
        ChatMessage chatMessage = objectMapper.convertValue(eventMessage.getData(), ChatMessage.class);
        chatService.savePrivateChat(chatMessage);
    }
}
