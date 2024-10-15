package com.mcug.minichat.chatService.event;

import com.mcug.minichat.chatService.entity.ChatMessage;
import com.mcug.minichat.globalConfig.rabbiMQ.RabbitMQConfig;
import com.mcug.minichat.utils.globeMessage.EventMessage;
import com.mcug.minichat.utils.globeMessage.EventService;
import org.springframework.stereotype.Service;

@Service
public class ChatEventService extends EventService {
    public void redirectionChat(ChatMessage eventMessage){
        sendMessage(RabbitMQConfig.SenderQueue, new EventMessage().setQueue("userSendingMessage").setData(eventMessage));
    }
}
