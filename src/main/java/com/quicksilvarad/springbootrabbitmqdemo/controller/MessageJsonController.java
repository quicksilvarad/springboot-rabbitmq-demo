package com.quicksilvarad.springbootrabbitmqdemo.controller;

import com.quicksilvarad.springbootrabbitmqdemo.dto.User;
import com.quicksilvarad.springbootrabbitmqdemo.publisher.RabbitMQJsonProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/json")
public class MessageJsonController {
    @Autowired
    private RabbitMQJsonProducer rabbitMQJsonProducer;
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageJsonController.class);
    @Autowired
    public MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer)
    {
        this.rabbitMQJsonProducer=rabbitMQJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String>sendJsonMessage(@RequestBody User user)
    {
        rabbitMQJsonProducer.sendMessage(user);
        return new ResponseEntity<>("Message sent successfully!", HttpStatus.OK);


    }
}
