package com.example.chataplication.Controller;

import com.example.chataplication.Dto.ChatNotification;
import com.example.chataplication.Entity.Messaging.ChatMessages;
import com.example.chataplication.Service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processChatMessages(@Payload ChatMessages chatMessages) {
        ChatMessages save = chatMessageService.save(chatMessages);
        messagingTemplate.convertAndSendToUser(
                chatMessages.getReceiverId(),
                "/queue/messages",
                ChatNotification.builder()
                        .id(save.getId())
                        .SenderId(save.getSenderId())
                        .ReceiverId(save.getReceiverId())
                        .Content(save.getContent())
                        .build()
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessages>> getChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
}
