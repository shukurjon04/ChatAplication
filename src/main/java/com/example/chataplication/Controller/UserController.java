package com.example.chataplication.Controller;

import com.example.chataplication.Entity.UserChat;
import com.example.chataplication.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public UserChat addUser(@Payload UserChat user) {
        return userService.saveUser(user);
    }

    @MessageMapping("/user.disconnect")
    @SendTo("/user/topic")
    public UserChat disconnect(@Payload UserChat user) {
        return userService.Disconnect(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserChat>> getUsers() {
        return ResponseEntity.ok(userService.findConnectedUsers());
    }
}
