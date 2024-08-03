package com.example.chataplication.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
public class UserChat {
    @Id
    private String nickName;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Status status;
}
