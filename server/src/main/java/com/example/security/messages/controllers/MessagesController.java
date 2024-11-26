package com.example.security.messages.controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.security.messages.command.DeleteMessageCommand;
import com.example.security.messages.services.MessagesService;
import com.example.security.messages.command.SendMessageCommand;
import com.example.security.auth.controllers.dtos.UpdateUserDto;
import com.example.security.messages.entities.Message;
import com.example.security.users.entities.User;
import com.example.security.auth.services.TokenService;
import com.example.security.users.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    private final TokenService tokenService;
    private final UsersService usersService;
    private final MessagesService messagesService;

    public MessagesController(TokenService tokenService, UsersService usersService, MessagesService chatService) {
        this.tokenService = tokenService;
        this.usersService = usersService;
        this.messagesService = chatService;
    }

    private User getUserIdFromToken(String token) {
        try {
            String decodedToken = tokenService.validateToken(token);
            UpdateUserDto userDto = new UpdateUserDto();
            userDto.setEmail(decodedToken);

            return (User) usersService.getUser(userDto);
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token validation failed", e);
        }
    }

    @PostMapping(path = "{receiverId}")
    public ResponseEntity<Map<String, String>> sendMessage(@PathVariable("receiverId") String receiverId,
                                         @RequestHeader(name = "Authorization") String token,
                                         @RequestBody MessageDto messageDto) {

        User decodedUser = getUserIdFromToken(token);
        UUID receiverIdConvert = UUID.fromString(receiverId);

        SendMessageCommand sendMessageCommand = new SendMessageCommand(this.messagesService, messageDto.getMessage(), decodedUser, receiverIdConvert);
        sendMessageCommand.execute();

        return ResponseEntity.ok(Map.of("message", "Message Sending!"));
    }

    @GetMapping(path = "{receiverId}")
    public ResponseEntity<List<Message>> getMessagesByReceiverIdAndSenderId(@PathVariable("receiverId") String receiverId,
                                                                @RequestHeader(name = "Authorization") String token) {
        User user = getUserIdFromToken(token);
        UUID receiverIdConvert = UUID.fromString(receiverId);

        List<Message> messages = this.messagesService.getMessagesReceiverAndSender(receiverIdConvert, user);
        return ResponseEntity.ok(messages);
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok(messagesService.getMessages());
    }

    @DeleteMapping(path = "{messageId}")
    public Object deleteMessage(@PathVariable("messageId") String messageId) {
        Optional<Message> existingMessage = this.messagesService.getMessage(UUID.fromString(messageId));

        existingMessage.ifPresent(message -> {
            DeleteMessageCommand deleteMessageCommand = new DeleteMessageCommand(this.messagesService, message);
            deleteMessageCommand.execute();
        });

        return Map.of("message", "Message Deleted");
    }
}
