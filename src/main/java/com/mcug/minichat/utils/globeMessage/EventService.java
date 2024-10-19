package com.mcug.minichat.utils.globeMessage;

import com.mcug.minichat.globalConfig.rabbiMQ.RabbitMQConfig;
import com.mcug.minichat.utils.entity.EventMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void receiveMessage(EventMessage eventMessage) {
        System.out.println("receiveServiceMessage: " + eventMessage.getEventName() + eventMessage.getData().toString());
    }

    public void sendMessage(String queue, EventMessage eventMessage) {
        rabbitTemplate.convertAndSend(queue, eventMessage);
//        System.out.println("sendServiceMessage: " + service + " " + message);
    }

    public void sendMessage(String exchange, String queue, EventMessage eventMessage) {
        rabbitTemplate.convertAndSend(exchange, queue, eventMessage);
    }
}
