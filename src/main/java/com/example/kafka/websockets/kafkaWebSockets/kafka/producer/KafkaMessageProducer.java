package com.example.kafka.websockets.kafkaWebSockets.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducer {


    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String topic;

    @Autowired
    public KafkaMessageProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate, @Value("${com.example.kafka.websockets.kafkaWebSockets.topic.messages}") String topic) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(Object message) throws JsonProcessingException {
        String stringMessage = objectMapper.writeValueAsString(message);
        kafkaTemplate.send(topic, stringMessage);
    }
}
