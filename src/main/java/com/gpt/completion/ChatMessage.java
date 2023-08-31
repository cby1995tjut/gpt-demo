package com.gpt.completion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class ChatMessage {


	@NonNull
	String role;
	@JsonInclude()
	String content;

	String name;
	@JsonProperty("function_call")
	ChatFunctionCall functionCall;

	public ChatMessage(String role, String content) {
		this.role = role;
		this.content = content;
	}

	public ChatMessage(String role, String content, String name) {
		this.role = role;
		this.content = content;
		this.name = name;
	}

}
