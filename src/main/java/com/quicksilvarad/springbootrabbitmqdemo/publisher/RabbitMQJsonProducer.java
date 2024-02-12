package com.quicksilvarad.springbootrabbitmqdemo.publisher;

import com.quicksilvarad.springbootrabbitmqdemo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String jsonKey;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(User user)
    {
        LOGGER.info(String.format("Message sent ->%s",user.toString()));
        rabbitTemplate.convertAndSend(exchange,jsonKey,user);
    }
}
