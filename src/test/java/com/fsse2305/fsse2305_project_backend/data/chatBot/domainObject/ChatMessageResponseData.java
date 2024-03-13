package com.fsse2305.fsse2305_project_backend.data.chatBot.domainObject;

import com.fsse2305.fsse2305_project_backend.data.chatBot.entity.ChatMessageEntity;

import java.time.LocalDateTime;

public class ChatMessageResponseData {
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public ChatMessageResponseData(ChatMessageEntity entity) {
        this.sender = entity.getSender();
        this.content = entity.getContent();
        this.timestamp = entity.getTimestamp();
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
