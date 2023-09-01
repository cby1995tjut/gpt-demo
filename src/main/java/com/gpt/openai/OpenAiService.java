package com.gpt.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gpt.completion.ChatMessage;
import com.gpt.completion.CompletionRequest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class OpenAiService {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String COMPLETION_REQUEST = "https://api.openai.com/v1/chat/completions";
    private String apiKey;
    private RestTemplate restTemplate;

    public OpenAiService() {
        this.apiKey = ""; // Your Api Key
        this.restTemplate = new RestTemplate();
    }

    //Todo it's better to define a openai completion response as return type
    public ResponseEntity<String> completionRequest(CompletionRequest completionRequest) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(mapper.writeValueAsString(createRequestBody(completionRequest.getMessages())), headers);
        return restTemplate.exchange(COMPLETION_REQUEST, HttpMethod.POST, requestEntity, String.class);
    }

    private ObjectNode createRequestBody(List<ChatMessage> messages) {
        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("temperature", 1);
        requestBody.put("top_p", 1);
        requestBody.put("frequency_penalty", 0);
        requestBody.put("presence_penalty", 0);

        requestBody.set("messages", mapper.convertValue(messages, ArrayNode.class));
        return requestBody;
    }

    // todo call by stream, it seems that it can be implement In theory
    public ResponseEntity<String> streamCompletionRequest(CompletionRequest completionRequest){
        return null;
    }

    //todo improvement performance, sync to async
    //todo duration

    //todo function call
}
