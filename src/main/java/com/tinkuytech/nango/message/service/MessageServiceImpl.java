package com.tinkuytech.nango.message.service;

import com.tinkuytech.nango.message.exception.MessageNotFoundException;
import com.tinkuytech.nango.message.model.Message;
import com.tinkuytech.nango.message.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message sendMessage(Message message) { return messageRepository.save(message); }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> getMessagesBySenderId(String senderId) {
        return messageRepository.findBySenderId(senderId);
    }

    @Override
    public List<Message> getMessagesByReceiverId(String receiverId) {
        return messageRepository.findByReceiverId(receiverId);
    }

    @Override
    public void deleteMessage(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new MessageNotFoundException(id);
        }
        messageRepository.deleteById(id);
    }
}
