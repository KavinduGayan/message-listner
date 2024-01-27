package org.example.rabbitmqproducer;

import org.example.rabbitmqproducer.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqProducerApplication implements CommandLineRunner {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        rabbitMQProducer.sendMessage("Hello, RabbitMQ!");
    }
}
