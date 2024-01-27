package org.example.rabbitmqproducer.controller;

import org.example.rabbitmqproducer.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    /**
 * This method is responsible for receiving a message from the client and sending it to the RabbitMQ queue.
 *
 * @param message the message sent by the client
 * @return a response indicating that the message was received successfully
 */
@PostMapping("/receive-message")
@ResponseStatus(HttpStatus.OK)
public Mono<String> receiveMessage(@RequestBody String message) {
    rabbitMQProducer.sendMessage(message);
    return Mono.just("Message received successfully");
}
}

