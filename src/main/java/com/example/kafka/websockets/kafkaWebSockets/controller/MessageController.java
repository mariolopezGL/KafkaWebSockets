package com.example.kafka.websockets.kafkaWebSockets.controller;


import com.example.kafka.websockets.kafkaWebSockets.dto.Message;
import com.example.kafka.websockets.kafkaWebSockets.kafka.producer.KafkaMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    private final KafkaMessageProducer kafkaMessageProducer;

    public MessageController(KafkaMessageProducer kafkaMessageProducer) {
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    @PostMapping
    public ResponseEntity createMessage(@RequestBody Message message) {

        try {
            message.setMessageTime(LocalDateTime.now());

            kafkaMessageProducer.send(message);

            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

}
