package com.mcug.minichat.globalConfig.rabbiMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnsCallback;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {
    public static final String CenterExchange = "CenterExchange";
    public static final String LoginQueue = "LoginQueue";
    public static final String SenderQueue = "SenderQueue";



    @Bean
    public Queue loginQueue() {
        return new Queue(LoginQueue);
    }
    @Bean
    public Queue senderQueue() {
        return new Queue(SenderQueue);
    }
    @Bean
    public DirectExchange centerExchanger() {
        return new DirectExchange(CenterExchange);
    }

    @Bean
    public Binding bindingLogin() {
        return BindingBuilder.bind(loginQueue()).to(centerExchanger()).with(LoginQueue);
    }

    @Bean
    public Binding bindingSender() {
        return BindingBuilder.bind(senderQueue()).to(centerExchanger()).with(SenderQueue);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        rabbitTemplate.setConfirmCallback(new ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (!ack) {
                    System.out.println("Message not delivered: " + cause);
                }
            }
        });
        rabbitTemplate.setReturnsCallback(new ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                System.out.println("Returned message: " + returned.getMessage());
            }
        });
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
