package com.tinkuytech.nango.message.repository;

import com.tinkuytech.nango.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderId(String senderId);
    List<Message> findByReceiverId(String receiverId);
}
