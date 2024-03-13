package com.fsse2305.fsse2305_project_backend.api;

import com.fsse2305.fsse2305_project_backend.service.ChatBotService;
import com.fsse2305.fsse2305_project_backend.config.EnvConfig;
import com.fsse2305.fsse2305_project_backend.data.chatBot.domainObject.ChatMessageRequestData;
import com.fsse2305.fsse2305_project_backend.data.chatBot.dto.request.ChatMessageRequestDto;
import com.fsse2305.fsse2305_project_backend.data.chatBot.dto.response.ChatMessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin({EnvConfig.devConfig, EnvConfig.prodConfig})
@RestController
public class ChatBotApi {

    private final ChatBotService chatBotService;

    @Autowired
    public ChatBotApi(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @PostMapping("/chatbot/message")
    public ChatMessageResponseDto handleChatMessage(@RequestBody ChatMessageRequestDto chatMessageRequestDto){
        return new ChatMessageResponseDto(chatBotService.handleChatMessage(new ChatMessageRequestData(chatMessageRequestDto)));
    }
}
