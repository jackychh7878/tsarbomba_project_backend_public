package com.fsse2305.fsse2305_project_backend.data.chatBot.dto.response;

import com.fsse2305.fsse2305_project_backend.data.chatBot.domainObject.ChatMessageResponseData;

import java.time.LocalDateTime;

public class ChatMessageResponseDto {
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public ChatMessageResponseDto(ChatMessageResponseData data) {
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
