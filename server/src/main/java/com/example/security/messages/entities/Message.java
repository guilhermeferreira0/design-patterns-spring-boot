package com.example.security.messages.entities;

import com.example.security.messages.MessageBuilder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID messageId;
    private UUID receiverId;
    private UUID senderId;
    private String message;
    @CreationTimestamp
    private Instant creationTimestamp;
    @UpdateTimestamp
    private Instant updateTimestamp;

    public Message() {
    }

    public Message(MessageBuilder builder) {
        this.messageId = builder.getMessageId();
        this.receiverId = builder.getReceiverId();
        this.senderId = builder.getSenderId();
        this.message = builder.getMessage();
        this.creationTimestamp = builder.getCreationTimestamp();
        this.updateTimestamp = builder.getUpdateTimestamp();
    }

    public UUID getMessageId() {
        return messageId;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }
}

