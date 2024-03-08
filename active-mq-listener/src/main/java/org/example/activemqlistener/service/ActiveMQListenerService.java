package org.example.activemqlistener.service;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQListenerService {

    @JmsListener(destination = "myQueue")
    public void receiveMessage(String message) {
        System.out.println("Received Message from ActiveMQ: " + message);
        // business logic
    }
}
