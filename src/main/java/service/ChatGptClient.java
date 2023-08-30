package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ChatGptClient {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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"; // 替换为你的 ChatGPT API 密钥

        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        String content = "Please summary following content in 30 words: <If the judge agrees, the trial could end up moving from state court to federal court, a more advantageous legal spot for Meadows. That would dramatically upend the case brought by Fulton County District Attorney Fani Willis just two weeks after the grand jury indicted Trump, Meadows and 17 others on racketeering charges related to efforts to subvert the 2020 election results.\n" +
                "\n" +
                "What unfolded in Atlanta on Monday was shocking by legal standards. Criminal defendants often take advantage of their constitutional right and decline to testify as part of their legal proceedings. And even smarter defendants decline to speak publicly about their case while it’s still ongoing.\n" +
                "\n" +
                "“It’s a calculated risk to put a defendant on the stand at any time, certainly during pre-trial motions. And usually, the calculation goes against the defendant,” J. Tom Morgan, a former district attorney in DeKalb County, Georgia, said on CNN’s “Inside Politics” Monday. “I’m absolutely amazed that he is taking the stand.”\n" +
                "\n" +
                "\n" +
                "Putting Meadows on the stand gave prosecutors the chance to question him about the events after the 2020 election in a setting where his words can be used both against him and the other defendants in the Fulton County case – not to mention in special counsel Jack Smith’s federal indictment of Trump for election subversion.\n" +
                "\n" +
                "Meadows is keen on moving the case out of state court because he’s arguing that he falls under a federal immunity claim extended, in certain contexts, to individuals who are prosecuted or sued for alleged conduct that was done on behalf of the US government or was tied to their federal position.\n" +
                "\n" +
                "The upcoming decision by US District Judge Steve Jones matters to all of the 19 co-defendants – including the former president, whose lawyers are expected to file a similar motion as Meadows. (One of Trump’s lawyers was spotted in the courthouse Monday.) Four additional defendants have already made a motion to move their cases to federal court, too.\n" +
                "\n" +
                "Jones, an appointee of former President Barack Obama, did not rule from the bench at the conclusion of Monday’s daylong hearing, but he noted the significance of the issue.\n" +
                "\n" +
                "When Meadows’ attorney said at the conclusion of the hearing that his client was “entitled to a prompt determination,” Jones responded that he was likely going to take his time because he thinks the ruling will set precedent for other cases. >";


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
