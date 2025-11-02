package com.tinkuytech.nango.message.service;

import com.tinkuytech.nango.message.exception.MessageNotFoundException;
import com.tinkuytech.nango.message.model.Message;
import com.tinkuytech.nango.message.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    void GetAllMessagesShouldReturnList() {

        Message msg1 = new Message(1L, "user1", "driver2", "Hola", LocalDateTime.now(), Message.Status.SENT);
        Message msg2 = new Message(2L, "user2", "driver3", "Adiós", LocalDateTime.now(), Message.Status.SENT);

        when(messageRepository.findAll()).thenReturn(Arrays.asList(msg1, msg2));

        List<Message> messages = messageService.getAllMessages();

        assertEquals(2, messages.size());
        verify(messageRepository, times(1)).findAll();
    }

    @Test
    void GetMessageByIdShouldReturnOptionalMessage() {

        Message msg = new Message(1L, "user1", "driver2", "Hola", LocalDateTime.now(), Message.Status.SENT);
        when(messageRepository.findById(1L)).thenReturn(Optional.of(msg));

        Optional<Message> result = messageService.getMessageById(1L);

        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getSenderId());
        verify(messageRepository, times(1)).findById(1L);
    }

    @Test
    void DeleteMessageShouldDeleteWhenExists() {
        
        when(messageRepository.existsById(1L)).thenReturn(true);

        messageService.deleteMessage(1L);

        verify(messageRepository, times(1)).deleteById(1L);
    }

    @Test
    void DeleteMessageShouldThrowExceptionWhenNotExists() {

        when(messageRepository.existsById(99L)).thenReturn(false);

        assertThrows(MessageNotFoundException.class, () -> messageService.deleteMessage(99L));

        verify(messageRepository, never()).deleteById(anyLong());
    }

    @Test
    void GetMessagesBySenderIdShouldReturnMessagesFromSender() {

        // Arrange
        String senderId = "user1";
        Message msg1 = new Message(1L, "user1", "driver2", "Hola!", LocalDateTime.now(), Message.Status.SENT);
        Message msg2 = new Message(2L, "user1", "driver3", "¿Cómo estás?", LocalDateTime.now(), Message.Status.SENT);

        when(messageRepository.findBySenderId(senderId)).thenReturn(Arrays.asList(msg1, msg2));

        // Act
        List<Message> result = messageService.getMessagesBySenderId(senderId);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(m -> m.getSenderId().equals(senderId)));
        verify(messageRepository, times(1)).findBySenderId(senderId);
    }
}
