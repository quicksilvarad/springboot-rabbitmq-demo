package com.quicksilvarad.springbootrabbitmqdemo.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

@Configuration
public class RabbitMQConfig {
    //Spring bean for rabbitmq queue

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing.key}")
    private String rountingKey;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.routing.json.key}")
    private String jsonKey;

    @Bean
    public Queue queue()
    {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange topicExchange()
    {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue jsonQueue()
    {
        return new Queue(jsonQueue);
    }


    @Bean
    public Binding binding()
    {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(rountingKey);
    }

    @Bean
    public Binding jsonBinding()
    {
        return BindingBuilder.bind(jsonQueue()).to(topicExchange()).with(jsonKey);
    }

    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
