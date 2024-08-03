package com.example.chataplication.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class ChatNotification {
    private String id;
    private String SenderId;
    private String ReceiverId;
    private String Content;
}
