package com.example.security.messages.command;

import com.example.security.messages.entities.Message;
import com.example.security.messages.services.MessagesService;

public class DeleteMessageCommand implements ICommand {
    private final MessagesService messagesService;
    private final Message message;

    public DeleteMessageCommand(MessagesService messagesService, Message message) {
        this.messagesService = messagesService;
        this.message = message;
    }

    @Override
    public void execute() {
        messagesService.deleteMessage(message);
    }
}
