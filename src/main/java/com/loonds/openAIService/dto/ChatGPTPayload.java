package com.loonds.openAIService.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTPayload {
    private String modal;
    private List<Message> messages;

    public ChatGPTPayload(String modal, String prompt) {
        this.modal = modal;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }
}
