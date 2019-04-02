package com.example.kafka.websockets.kafkaWebSockets.kafka.listener;

import com.example.kafka.websockets.kafkaWebSockets.dto.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;


@Service
public class KafkaMessageListener {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    public KafkaMessageListener(ObjectMapper objectMapper, SimpMessageSendingOperations messagingTemplate) {
        this.objectMapper = objectMapper;
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "${com.example.kafka.websockets.kafkaWebSockets.topic.messages}",
            groupId = "${spring.kafka.consumer.groupId}")
    public void receiveMessage(String kafkaMessage) throws Exception {


        Message message =
                objectMapper.readValue(kafkaMessage, Message.class);

        messagingTemplate.convertAndSend("/queue/messages",
                objectMapper.writeValueAsString(message));

    }
}
