package main.java.api;


import okhttp3.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


// TODO: Each test costs 17 tokens, have 4096 total
public class ChatAPI {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();

        String apiKey = "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";
        String prompt = "Create a single summary question based on the provided questions: Hi? No";
        String model = "gpt-3.5-turbo";

        try{
            //HTTP Post request
            URL obj = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            //building request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            //Getting response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            int start = response.indexOf("content")+11; //Where the content starts.
            int end = response.indexOf("\"", start); // Where the content ends.
            System.out.println(response.substring(start, end));
        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }
}

