package com.loonds.openAIService.controller;

import com.loonds.openAIService.dto.ChatGPTPayload;
import com.loonds.openAIService.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bot")
public class ChatController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.modal}")
    private String modal;
    @Value(("${openai.api.url}"))
    private String apiURL;

    @GetMapping("/chat")
    public String chatWithGPT(@RequestParam("prompt") String prompt) {
        ChatGPTPayload payload = new ChatGPTPayload(modal, prompt);
        ChatGptResponse response = restTemplate.postForObject(apiURL, payload, ChatGptResponse.class);
        return response.getChoices().get(0).getMessage().getContent();
    }
}
