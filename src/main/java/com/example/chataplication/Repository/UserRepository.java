package com.example.chataplication.Repository;

import com.example.chataplication.Entity.Status;
import com.example.chataplication.Entity.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserChat, String> {

    List<UserChat> findAllByStatus(Status status);
}
