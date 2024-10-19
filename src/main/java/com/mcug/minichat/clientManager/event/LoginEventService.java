package com.mcug.minichat.clientManager.event;

import com.mcug.minichat.clientManager.ClientService;
import com.mcug.minichat.globalConfig.rabbiMQ.RabbitMQConfig;
import com.mcug.minichat.utils.EventName;
import com.mcug.minichat.utils.entity.ChatMessage;
import com.mcug.minichat.utils.entity.EventMessage;
import com.mcug.minichat.utils.globeMessage.EventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class LoginEventService extends EventService {

    /*
    * 监听登录队列
     */
    @RabbitListener(queues = RabbitMQConfig.LoginQueue)
    public void eventListener(EventMessage eventMessage) {
        receiveMessage(eventMessage);
    }



    @Override
    public void receiveMessage(EventMessage eventMessage) {
        if (eventMessage.getEventName().equals(EventName.USER_LOGIN)) {
            ClientService.addReadyClient(eventMessage.getData().toString());
            return;
        }
    }


}
