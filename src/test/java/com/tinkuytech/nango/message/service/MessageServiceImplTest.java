package com.tinkuytech.nango.message.service;

import com.tinkuytech.nango.message.model.Message;
import com.tinkuytech.nango.message.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessageServiceImplTest {

    private MessageRepository messageRepository;
    private MessageServiceImpl messageService;

    @BeforeEach
    void setUp() {
        messageRepository = mock(MessageRepository.class);
        messageService = new MessageServiceImpl(messageRepository);
    }

    @Test
    void SendMessageShouldSaveAndReturnMessage() {

        // Arrange
        Message message = new Message();
        message.setSenderId("user1");
        message.setReceiverId("driver2");
        message.setContent("Hola!");

        when(messageRepository.save(any(Message.class))).thenAnswer(invocation -> {
            Message msg = invocation.getArgument(0);
            msg.setId(1L);
            msg.setMessageTime(LocalDateTime.now());
            return msg;
        });

        // Act
        Message savedMessage = messageService.sendMessage(message);

        // Assert
        assertNotNull(savedMessage.getId());
        assertEquals("user1", savedMessage.getSenderId());
        assertEquals("driver2", savedMessage.getReceiverId());
        assertEquals("Hola!", savedMessage.getContent());
        assertNotNull(savedMessage.getMessageTime());

        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository, times(1)).save(captor.capture());
        assertEquals("user1", captor.getValue().getSenderId());
    }
}
