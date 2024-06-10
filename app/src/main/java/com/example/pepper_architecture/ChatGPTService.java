package com.example.pepper_architecture;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

    public class ChatGPTService {
        private static final String API_KEY = "API key";  //  API key
        private static final String API_URL = "https://api.openai.com/v1/chat/completions";

        public static void sendMessage(String message, Callback callback) {
            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");

            JSONObject jsonObject = new JSONObject();
            JSONArray messagesArray = new JSONArray();
            JSONObject messageContent = new JSONObject();

            try {
                messageContent.put("role", "user");
                messageContent.put("content", message);
                messagesArray.put(messageContent);

                jsonObject.put("model", "gpt-3.5-turbo");  // Use the appropriate model ID
                jsonObject.put("messages", messagesArray);
                jsonObject.put("max_tokens", 150);

                RequestBody body = RequestBody.create(jsonObject.toString(), JSON);
                Request request = new Request.Builder()
                        .url(API_URL)
                        .post(body)
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .build();

                client.newCall(request).enqueue(callback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


