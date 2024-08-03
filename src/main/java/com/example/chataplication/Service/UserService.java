package com.example.chataplication.Service;

import com.example.chataplication.Entity.Status;
import com.example.chataplication.Entity.UserChat;
import com.example.chataplication.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.chataplication.Entity.Status.OFFLINE;
import static com.example.chataplication.Entity.Status.ONLINE;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserChat saveUser(UserChat user) {
        user.setStatus(ONLINE);
        return userRepository.save(user);
    }

    public UserChat Disconnect(UserChat user) {
        var storedUser = userRepository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(OFFLINE);
            return userRepository.save(storedUser);
        }
        return null;
    }

    public List<UserChat> findConnectedUsers() {
        return userRepository.findAllByStatus(ONLINE);
    }
}
