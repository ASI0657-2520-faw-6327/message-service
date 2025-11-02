package com.tinkuytech.nango.message.service;

import com.tinkuytech.nango.message.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Message sendMessage(Message message);
    List<Message> getAllMessages();
    Optional<Message> getMessageById(Long id);
    List<Message> getMessagesBySenderId(String senderId);
    List<Message> getMessagesByReceiverId(String receiverId);
    void deleteMessage(Long id);
}
