package com.example.security.messages;

import com.example.security.messages.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessagesRepository extends JpaRepository<Message, UUID> {
    List<Message> findByReceiverIdAndSenderId(UUID receiverId, UUID senderId);
}
