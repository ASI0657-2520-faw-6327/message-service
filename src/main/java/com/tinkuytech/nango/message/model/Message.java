package com.tinkuytech.nango.message.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_user_id", nullable = false)
    private String senderId;

    @Column(name = "receiver_user_id", nullable = false)
    private String receiverId;

    @Column(nullable = false)
    private String content;

    @Column(name = "message_time", nullable = false)
    private LocalDateTime messageTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        SENT, DELIVERED, FAILED
    }

    public Message() {}

    public Message(Long id, String senderId, String receiverId, String content, LocalDateTime messageTime, Status status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.messageTime = messageTime;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getReceiverId() { return receiverId; }
    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getMessageTime() { return messageTime; }
    public void setMessageTime(LocalDateTime messageTime) { this.messageTime = messageTime; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
