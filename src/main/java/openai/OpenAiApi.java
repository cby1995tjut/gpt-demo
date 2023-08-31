package openai;

import com.gpt.completion.CompletionRequest;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

public interface OpenAiApi {

    @Streaming
    @POST("/v1/completions")
    Call<ResponseBody> createCompletionStream(@Body CompletionRequest request);

}
