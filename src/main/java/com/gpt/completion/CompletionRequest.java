package com.gpt.completion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompletionRequest {

    String model;

    List<ChatMessage> messages;

    Double temperature;

    @JsonProperty("top_p")
    Double topP;

    Integer n;

    Boolean stream;

    List<String> stop;

    @JsonProperty("max_tokens")
    Integer maxTokens;

    @JsonProperty("presence_penalty")
    Double presencePenalty;

    @JsonProperty("frequency_penalty")
    Double frequencyPenalty;

    @JsonProperty("logit_bias")
    Map<String, Integer> logitBias;

    String user;

    List<ChatFunction> functions;


    @JsonProperty("function_call")
    ChatCompletionRequestFunctionCall functionCall;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatCompletionRequestFunctionCall {
        String name;

        public static ChatCompletionRequestFunctionCall of(String name) {
            return new ChatCompletionRequestFunctionCall(name);
        }

    }
}
