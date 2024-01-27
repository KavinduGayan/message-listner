package org.example.rabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Counter messagesSentCounter;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate, MeterRegistry meterRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.messagesSentCounter = Counter.builder("rabbitmq.messages.sent")
                .description("Total number of messages sent to RabbitMQ")
                .register(meterRegistry);
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("myQueue", message);
        System.out.println("Message sent to RabbitMQ: " + message);
        messagesSentCounter.increment();
    }
}
