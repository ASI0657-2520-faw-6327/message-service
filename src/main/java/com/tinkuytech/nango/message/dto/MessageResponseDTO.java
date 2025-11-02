package com.tinkuytech.nango.message.dto;

import com.tinkuytech.nango.message.model.Message;

import java.time.LocalDateTime;

public class MessageResponseDTO {

    private Long id;
    private String senderId;
    private String receiverId;
    private String content;
    private LocalDateTime messageTime;
    private Message.Status status;

    public MessageResponseDTO() {}

    public MessageResponseDTO(Message message) {
        this.id = message.getId();
        this.senderId = message.getSenderId();
        this.receiverId = message.getReceiverId();
        this.content = message.getContent();
        this.messageTime = message.getMessageTime();
        this.status = message.getStatus();
    }

    public Long getId() { return id; }

    public String getSenderId() { return senderId; }

    public String getReceiverId() { return receiverId; }

    public String getContent() { return content; }

    public LocalDateTime getMessageTime() { return messageTime; }

    public Message.Status getStatus() { return status; }
}
