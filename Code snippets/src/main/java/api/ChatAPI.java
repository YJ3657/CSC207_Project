package main.java.api;


import okhttp3.*;

import java.io.IOException;

public class ChatAPI {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();

        String apiKey = "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";
        String prompt = "Hi";
        String model = "gpt-3.5-turbo";

        // Make a POST request to the OpenAI API
        RequestBody body = new FormBody.Builder()
                .add("model", model)
                .add("prompt", prompt)
                .build();

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println(response.body().string());
            } else {
                System.out.println("Error: " + response.code() + " - " + response.message());
                System.out.println(response.body().string()); // Print the response body for more details
            }
        }
    }
}

