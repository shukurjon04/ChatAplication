package com.example.chataplication.Service;

import com.example.chataplication.Entity.Messaging.ChatMessages;
import com.example.chataplication.Repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessages save(ChatMessages chatMessages) {
        var ChatId = chatRoomService.getRoomId(chatMessages.getSenderId(), chatMessages.getReceiverId(),true).orElseThrow();
        chatMessages.setChatId(ChatId);
        return chatMessageRepository.save(chatMessages);
    }

    public List<ChatMessages> findChatMessages(String senderId, String receiverId) {
        var ChatId = chatRoomService.getRoomId(senderId, receiverId,false);
        return ChatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
