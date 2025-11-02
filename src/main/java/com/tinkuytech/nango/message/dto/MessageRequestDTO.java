package com.tinkuytech.nango.message.dto;

import java.time.LocalDateTime;

public class MessageRequestDTO {

    private String senderId;
    private String receiverId;
    private String content;
    private LocalDateTime messageTime;

    public MessageRequestDTO() {}

    public MessageRequestDTO(String senderId, String receiverId, String content, LocalDateTime messageTime) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.messageTime = messageTime;
    }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getReceiverId() { return receiverId; }
    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content;}

    public LocalDateTime getMessageTime() { return messageTime; }
    public void setMessageTime(LocalDateTime messageTime) { this.messageTime = messageTime; }

    @Override
    public String toString() {
        return "MessageRequestDTO{" +
                "senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", content='" + content + '\'' +
                ", messageTime=" + messageTime +
                '}';
    }
}
