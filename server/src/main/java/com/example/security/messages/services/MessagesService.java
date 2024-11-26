package com.example.security.messages.services;

import com.example.security.messages.MessageBuilder;
import com.example.security.messages.MessagesRepository;
import com.example.security.messages.entities.Message;
import com.example.security.users.entities.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessagesService {
    private final MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void sendMessage(User user, UUID receiverId, String message) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.setMessageId(UUID.randomUUID());
        messageBuilder.setMessage(message);
        messageBuilder.setSenderId(user.getUserId());
        messageBuilder.setReceiverId(receiverId);
        messageBuilder.setCreationTimestamp(Instant.now());
        messageBuilder.setUpdateTimestamp(null);

        Message newMessage = messageBuilder.builder();

        this.messagesRepository.save(newMessage);
    }

    public void deleteMessage(Message message) {
        this.messagesRepository.deleteById(message.getMessageId());
    }

    public List<Message> getMessages() {
        return this.messagesRepository.findAll();
    }

    public Optional<Message> getMessage(UUID messageId) {
        return this.messagesRepository.findById(messageId);
    }

    public List<Message> getMessagesReceiverAndSender(UUID receiverId, User user) {
        List<Message> messagesReceiver = this.messagesRepository.findByReceiverIdAndSenderId(receiverId, user.getUserId());
        List<Message> messagesSender = this.messagesRepository.findByReceiverIdAndSenderId(user.getUserId(), receiverId);

        List<Message> combinedMessages = new ArrayList<>();
        combinedMessages.addAll(messagesReceiver);
        combinedMessages.addAll(messagesSender);

        return combinedMessages;
    }
}
