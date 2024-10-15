package com.mcug.minichat.utils.globeMessage;

import ch.qos.logback.core.boolex.EventEvaluatorBase;
import com.mcug.minichat.globalConfig.rabbiMQ.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void receiveMessage(EventMessage eventMessage) {
        System.out.println("receiveServiceMessage: " + eventMessage.getQueue() + eventMessage.data.toString());
    }

    public void sendMessage(String queue, EventMessage eventMessage) {
        rabbitTemplate.convertAndSend(queue, eventMessage);
//        System.out.println("sendServiceMessage: " + service + " " + message);
    }
}
