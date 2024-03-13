package com.fsse2305.fsse2305_project_backend.data.chatBot.domainObject;

import com.fsse2305.fsse2305_project_backend.data.chatBot.dto.request.ChatMessageRequestDto;

public class ChatMessageRequestData {
    private String sender;
    private String content;

    public ChatMessageRequestData(ChatMessageRequestDto dto) {
        this.sender = "Customer";
        this.content = dto.getContent();
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
}
