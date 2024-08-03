package com.example.chataplication.Repository;

import com.example.chataplication.Entity.Messaging.ChatMessages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessages,String> {
    List<ChatMessages> findByChatId(String s);
}
