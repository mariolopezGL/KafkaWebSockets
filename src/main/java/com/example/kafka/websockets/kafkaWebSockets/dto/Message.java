package com.example.kafka.websockets.kafkaWebSockets.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private String message;
    private LocalDateTime messageTime;
    private String sender;
}