package com.gpt.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gpt.completion.ChatMessageRole;
import com.gpt.completion.CompletionRequest;
import com.gpt.completion.ChatMessage;
import com.gpt.openai.OpenAiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompletionDemo {

    public static void main(String[] args) throws JsonProcessingException {

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

        OpenAiService openAiService = new OpenAiService();
        ResponseEntity<String> responseEntity = openAiService.completionRequest(chatCompletionRequest);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            System.out.println("Response: " + responseBody);
        } else {
            System.out.println("Request failed with status code: " + responseEntity.getStatusCode());
        }
    }
}
