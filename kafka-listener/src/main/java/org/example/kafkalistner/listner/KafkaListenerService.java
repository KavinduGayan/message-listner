package org.example.kafkalistner.listner;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @KafkaListener(topics = "your_topic_name", groupId = "group_id")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
        // Add your business logic here
    }
}

