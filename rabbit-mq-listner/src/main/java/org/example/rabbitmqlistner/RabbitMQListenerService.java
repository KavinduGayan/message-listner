package org.example.rabbitmqlistner;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListenerService {

    @RabbitListener(queues = "your_queue_name")
    public void listen(String message) {
        System.out.println("Received Message from RabbitMQ: " + message);
        // Add your business logic here
    }
}
