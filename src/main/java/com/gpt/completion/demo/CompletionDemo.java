package com.gpt.completion.demo;

import com.gpt.completion.ChatMessageRole;
import com.gpt.completion.CompletionRequest;
import com.gpt.completion.ChatMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompletionDemo {

    public static void main(String[] args) {

        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "Summary three body.");
        messages.add(systemMessage);

        CompletionRequest chatCompletionRequest = CompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(50)
                .logitBias(new HashMap<>())
                .build();


    }
}
