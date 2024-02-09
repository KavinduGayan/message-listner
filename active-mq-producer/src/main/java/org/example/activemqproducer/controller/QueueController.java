package org.example.activemqproducer.controller;

import org.example.activemqproducer.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueController {

    private MessageProducer messageProducer;

    @Autowired
    public QueueController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/receive-message")
    @ResponseStatus(HttpStatus.OK)
    public String receiveMessage(@RequestBody String message) {
        messageProducer.sendMessage(message);
        return "Message received successfully";
    }
}
