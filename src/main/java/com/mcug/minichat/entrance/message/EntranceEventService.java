package com.mcug.minichat.entrance.message;

import com.mcug.minichat.globalConfig.rabbiMQ.RabbitMQConfig;
import com.mcug.minichat.utils.globeMessage.EventMessage;
import com.mcug.minichat.utils.globeMessage.EventService;
import org.springframework.stereotype.Service;

@Service
public class EntranceEventService extends EventService {
    public void userReadyConnect(String token) {
        sendMessage(RabbitMQConfig.LoginQueue, new EventMessage().setQueue("userReadyConnect").setData(token));
    }
}
