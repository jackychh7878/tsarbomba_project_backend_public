package com.fsse2305.fsse2305_project_backend.data.chatBot.entity;

import com.fsse2305.fsse2305_project_backend.data.chatBot.domainObject.ChatMessageRequestData;

import java.time.LocalDateTime;

public class ChatMessageEntity {
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public ChatMessageEntity(){};

    public ChatMessageEntity(ChatMessageRequestData data) {
        this.sender = data.getSender();
        this.content = data.getContent();
        this.timestamp = LocalDateTime.now();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
