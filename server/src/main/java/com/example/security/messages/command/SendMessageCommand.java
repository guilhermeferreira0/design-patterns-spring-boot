package com.example.security.messages.command;

import com.example.security.messages.services.MessagesService;
import com.example.security.users.entities.User;

import java.util.UUID;

public class SendMessageCommand implements ICommand {
    private final MessagesService messagesService;
    private final String message;
    private final User user;
    private final UUID receiverId;

    public SendMessageCommand(MessagesService chatService, String message, User user, UUID receiverId) {
        this.messagesService = chatService;
        this.message = message;
        this.user = user;
        this.receiverId = receiverId;
    }

    @Override
    public void execute() {
        messagesService.sendMessage(user, receiverId, message);
    }
}
