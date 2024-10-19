package com.mcug.minichat.entrance.event;

import com.mcug.minichat.globalConfig.rabbiMQ.RabbitMQConfig;
import com.mcug.minichat.utils.EventName;
import com.mcug.minichat.utils.entity.EventMessage;
import com.mcug.minichat.utils.globeMessage.EventService;
import org.springframework.stereotype.Service;

@Service
public class EntranceEventService extends EventService {
    public void userReadyConnect(String token) {
        sendMessage(RabbitMQConfig.LoginQueue, new EventMessage().setEventName(EventName.USER_LOGIN).setData(token));
    }
}
