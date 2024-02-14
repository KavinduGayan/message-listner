package org.example.rabbitmqlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

@SpringBootApplication
public class RabbitMqListnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqListnerApplication.class, args);
    }

}
