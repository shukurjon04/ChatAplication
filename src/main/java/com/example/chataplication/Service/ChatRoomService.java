package com.example.chataplication.Service;

import com.example.chataplication.Entity.Room.ChatRoom;
import com.example.chataplication.Repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static javax.management.Query.or;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Optional<String> getRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {

        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(
                        () -> {
                            if (createNewRoomIfNotExists) {
                                var chatId = CreatChat(senderId, recipientId);
                                return Optional.of(chatId);
                            }
                            return Optional.empty();
                        }
                );

    }

    private String CreatChat(String senderId, String recipientId) {
        var Chatid = String.format("%s_%s", senderId, recipientId);

        ChatRoom SenderRecipient = ChatRoom.builder()
                .chatId(Chatid)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();
        ChatRoom RecipientSender = ChatRoom.builder()
                .chatId(Chatid)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        chatRoomRepository.save(SenderRecipient);
        chatRoomRepository.save(RecipientSender);
        return Chatid;
    }
}
