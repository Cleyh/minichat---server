package com.mcug.minichat.clientManager.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcug.minichat.clientManager.ClientService;
import com.mcug.minichat.globalConfig.rabbiMQ.RabbitMQConfig;
import com.mcug.minichat.utils.EventName;
import com.mcug.minichat.utils.entity.ChatMessage;
import com.mcug.minichat.utils.entity.EventMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PublicChatEventService extends ChatEventService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @RabbitListener(queues = RabbitMQConfig.PublicSenderQueue)
    public void publicChatListener(EventMessage eventMessage) throws IOException {
        receivePublicChatMessage(eventMessage);
    }

    private void receivePublicChatMessage(EventMessage eventMessage) throws IOException {
        ChatMessage chatMessage = objectMapper.convertValue(eventMessage.getData(), ChatMessage.class);
        ClientService.sendPublicChatToClient(chatMessage);
    }

    public void readySendPublicChatMessage(ChatMessage chatMessage) {
        sendMessage(RabbitMQConfig.PublicChatExchange,
                "",
                new EventMessage().setEventName(EventName.PUBLIC_CHAT_EVENT).setData(chatMessage));
    }
}
