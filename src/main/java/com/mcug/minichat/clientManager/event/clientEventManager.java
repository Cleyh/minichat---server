package com.mcug.minichat.clientManager.event;

import com.mcug.minichat.clientManager.ClientService;
import com.mcug.minichat.clientManager.entity.Client;
import com.mcug.minichat.globalConfig.rabbiMQ.RabbitMQConfig;
import com.mcug.minichat.utils.globeMessage.EventMessage;
import com.mcug.minichat.utils.globeMessage.EventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class clientEventManager extends EventService {
    @RabbitListener(queues = RabbitMQConfig.LoginQueue)
    public void eventListener(EventMessage eventMessage) {
        receiveMessage(eventMessage);
    }

    @Override
    public void receiveMessage(EventMessage eventMessage) {
        if (eventMessage.getQueue().equals("userReadyConnect")) {
            ClientService.addReadyClient(eventMessage.getData().toString());
            return;
        }
    }
}
