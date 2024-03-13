package com.fsse2305.fsse2305_project_backend.service;

import com.fsse2305.fsse2305_project_backend.data.chatBot.domainObject.ChatMessageRequestData;
import com.fsse2305.fsse2305_project_backend.data.chatBot.domainObject.ChatMessageResponseData;
import com.fsse2305.fsse2305_project_backend.data.chatBot.entity.ChatMessageEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatBotServiceImpl implements ChatBotService {
    private final ProductService productService;

    public ChatBotServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ChatMessageResponseData handleChatMessage(ChatMessageRequestData data){
        String content = data.getContent().toLowerCase();
        String response;

        if (content.contains("customer service")) {
            response = "Our customer service team is available 24/7. How can we assist you today?";
        } else if (content.contains("faq")) {
            response = "Here are some frequently asked questions: [FAQs]";
        } else if (content.contains("recommend")) {
            response = "Based on your preferences, we recommend [recommended products].";
        } else if (content.contains("feedback")) {
            response = "We value your feedback! Please share your thoughts or suggestions.";
        } else {
            response = "I'm sorry, but I didn't understand your message. How can I assist you?";
        }

        ChatMessageEntity chatbotResponse = new ChatMessageEntity();
        chatbotResponse.setSender("Chatbot");
        chatbotResponse.setContent(response);

        return new ChatMessageResponseData(chatbotResponse);

    }
}
