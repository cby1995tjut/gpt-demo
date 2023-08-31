package com.gpt.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ChatGptClientDemo {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String apiKey = ""; // 替换为你的 ChatGPT API 密钥

        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        String content = "Summary three body";


        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("temperature", 1);
        requestBody.put("top_p", 1);
        requestBody.put("frequency_penalty", 0);
        requestBody.put("presence_penalty", 0);
        ArrayNode messages = objectMapper.createArrayNode();

        ObjectNode message = objectMapper.createObjectNode();
        message.put("role", "system");
        message.put("content", content);

        messages.add(message);
        requestBody.set("messages", messages);

        System.out.println(objectMapper.writeValueAsString(requestBody));

        HttpEntity<String> requestEntity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            System.out.println("Response: " + responseBody);
        } else {
            System.out.println("Request failed with status code: " + responseEntity.getStatusCode());
        }
    }
}
