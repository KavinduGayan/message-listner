package org.example.rabbitmqlistner.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListenerService {

    @RabbitListener(queues = "myQueue")
    public void listen(String message) {
        System.out.println("Received Message from RabbitMQ: " + message);
        // Add your business logic here
    }
}
