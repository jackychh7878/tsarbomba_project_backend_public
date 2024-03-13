package com.fsse2305.fsse2305_project_backend.service;

import com.fsse2305.fsse2305_project_backend.data.chatBot.domainObject.ChatMessageRequestData;
import com.fsse2305.fsse2305_project_backend.data.chatBot.domainObject.ChatMessageResponseData;

public interface ChatBotService {
    ChatMessageResponseData handleChatMessage (ChatMessageRequestData data);
}
