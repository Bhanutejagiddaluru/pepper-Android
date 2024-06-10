package com.example.pepper_architecture;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.aldebaran.qi.sdk.object.conversation.Phrase;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import java.io.IOException;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.conversation.Say;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import com.aldebaran.qi.sdk.builder.PhraseSetBuilder;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.conversation.Listen;
import com.aldebaran.qi.sdk.object.conversation.PhraseSet;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.builder.ChatBuilder;
import com.aldebaran.qi.sdk.builder.QiChatbotBuilder;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.builder.TopicBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.object.conversation.QiChatVariable;
import com.aldebaran.qi.sdk.object.conversation.QiChatbot;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.conversation.Topic;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import java.io.IOException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.builder.ListenBuilder;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.conversation.Listen;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Call;
import okhttp3.OkHttpClient;

import okhttp3.Callback;
import okhttp3.Response;
import android.util.Log;
import android.widget.TextView;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.ListenBuilder;

import com.aldebaran.qi.sdk.object.conversation.Say;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.app.Activity;         // Basic Android Activity class
import android.os.Bundle;           // For handling instance state
import android.content.Intent;      // For launching new activities and intents
import android.net.Uri;            // For handling URI objects
import android.view.View;          // Base class for widgets, which are used to create interactive UI components
import android.widget.Button;      // Button component
import android.widget.Toast;       // For displaying toast messages
import android.view.View.OnClickListener;  // For handling click events
import android.app.Activity;         // Basic Android Activity class
import android.os.Bundle;           // For handling instance state
import android.widget.Toast;        // For displaying toast messages
import android.os.Handler;          // To handle operations on the UI thread
import java.net.HttpURLConnection;  // For HTTP connections
import java.net.URL;                // To use Java URL class
import java.io.IOException;         // For handling input-output exceptions


public class MainActivity  extends RobotActivity implements RobotLifecycleCallbacks {

    private static final String TAG = "MainActivity";

    // The QiContext provided by the QiSDK.
    private QiContext qiContext = null;

    // Store the predefine text
    private Say sayAction, sayAction1, sayAction2;;

    // Textview element in the GUI
    private TextView textView;
    private ImageView imageView;
    private TextView ResponseView;


    // Store the Chat action.
    private Chat chatAction;

    // Animate actions
    private Animate goodAnimAction, badAnimAction, handshakeAnimAction, pointscreenAnimAction;

    private static final int REQUEST_MICROPHONE = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView1);
        imageView = (ImageView)findViewById(R.id.imageView2);
        ResponseView = findViewById(R.id.textView2);

        checkMicrophonePermission();

        QiSDK.register(this, this);
    }

    private void checkMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_MICROPHONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @Nullable String[] permissions, @Nullable int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_MICROPHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "Microphone permission granted.");
            } else {
                Log.e(TAG, "Microphone permission denied.");
            }
        }
    }



    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        Log.i(TAG, "App started.");

        // Store the provided QiContext.
        this.qiContext = qiContext;

        // Initialize all actions.
        initActions();

//        listenAndRespond();

        // Run the Say action asynchronously and handle completion.
        sayAction1.async().run().thenConsume(future -> {
            if (future.isSuccess()) {
                Log.i(TAG, "Greeting completed successfully.");

                // Run the Chat action asynchronously.
                Future<Void> chatFuture = chatAction.async().run();

                // Add a lambda to the action execution.
                chatFuture.thenConsume(chatFutureResult -> {
                    if (chatFutureResult.hasError()) {
                        Log.e(TAG, "Discussion finished with error.", chatFutureResult.getError());
                    }
                });
            } else if (future.hasError()) {
                Log.e(TAG, "Greeting finished with error.", future.getError());
            }
        });
    }

    private void textViewSetValue(String val) {
        runOnUiThread(() -> {
            textView.setText(val);
            //interactWithChatGPT(val);
        });
    }

    private void ResponseViewSetValue(String val) {
        runOnUiThread(() -> {
            //ResponseView.setText(val);
            //interactWithChatGPT(val);
        });
    }

    private void imageViewSetValue(String val) {
        if (val.equals("ai"))
            runOnUiThread(() -> { imageView.setImageResource(R.drawable.ai); });
        else if (val.equals("neha"))
            runOnUiThread(() -> { imageView.setImageResource(R.drawable.neha); });
        else if (val.equals("course"))
            runOnUiThread(() -> { imageView.setImageResource(R.drawable.coke); });
        else if (val.equals("class"))
            runOnUiThread(() -> { imageView.setImageResource(R.drawable.wine); });

    }


//    private void initialAction() {
//        sayAction1 = SayBuilder.with(qiContext)
//                .withText("Please tell me your query.")
//                .build();
//        sayAction1.run();
//    }


    private void listenAndRespond(String val) {

//        runOnUiThread(() -> interactWithChatGPT("Hello "+currentValue));

        runOnUiThread(() -> interactWithChatGPT(val));
    }



    private void interactWithChatGPT(String query) {
        // Run network interactions in a background thread
        new Thread(() -> {
            ChatGPTService.sendMessage(query, new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful() && response.body() != null) {
                        final String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            final String textResponse = jsonObject.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");

                            // Create and run Say action in the background thread
                            Say sayAction = SayBuilder.with(qiContext)
                                    .withText(textResponse)
                                    .build();
                            sayAction.async().run();

                            // Update UI on the main thread
                            runOnUiThread(() -> ResponseView.setText(textResponse));

                        } catch (JSONException e) {
                            Log.e(TAG, "Failed to parse JSON response: " + e.getMessage());
                            runOnUiThread(() -> textView.setText("Failed to parse JSON response."));
                        }
                    } else {
                        Log.e(TAG, "Response not successful: " + response.code());
                        runOnUiThread(() -> textView.setText("Error in response. Code: " + response.code()));
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "Failed to send message: " + e.getMessage());
                    runOnUiThread(() -> textView.setText("Failed to get response."));
                }
            });
        }).start();
    }



    private void turnOn() {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://www.virtualsmarthome.xyz/url_routine_trigger/activate.php?trigger=c78f9af2-9a92-48c8-974d-db052187742c&token=05323cc8-5a94-4737-a488-905c79819422&response=html");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET"); // Use GET method
                connection.connect();

                int responseCode = connection.getResponseCode(); // Get the response code
                if (responseCode == HttpURLConnection.HTTP_OK) { // Check if the request was successful
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "URL triggered successfully", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to trigger URL: " + responseCode, Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error triggering URL: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            } finally {
                if (connection != null) {
                    connection.disconnect(); // Disconnect after completing the request
                }
            }
        }).start();
    }

    private void lightoff() {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://www.virtualsmarthome.xyz/url_routine_trigger/activate.php?trigger=dae68d05-5d0f-403d-8bc0-19eae0332374&token=4b41d445-a73e-47fe-8563-4096fad81e63&response=html");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET"); // Use GET method
                connection.connect();

                int responseCode = connection.getResponseCode(); // Get the response code
                if (responseCode == HttpURLConnection.HTTP_OK) { // Check if the request was successful
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "URL triggered successfully", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to trigger URL: " + responseCode, Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error triggering URL: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            } finally {
                if (connection != null) {
                    connection.disconnect(); // Disconnect after completing the request
                }
            }
        }).start();
    }



    public void initActions() {
        // Create a chat topic
        Topic topic = TopicBuilder.with(qiContext) // Create the builder using the QiContext.
                .withResource(R.raw.telugu) // Set the topic resource.
                .build(); // Build the topic.
        // Create a new QiChatbot.
        QiChatbot qiChatbot = QiChatbotBuilder.with(qiContext)
                .withTopic(topic)
                .build();
        // Create a new Chat action.
        chatAction = ChatBuilder.with(qiContext)
                .withChatbot(qiChatbot)
                .build();


        // Add an on started listener to the Chat action.
        chatAction.addOnStartedListener(() -> Log.i(TAG, "Discussion started."));

        chatAction.addOnFallbackReplyFoundForListener(input -> {
            ResponseViewSetValue("give in 5 bullet short points " + input);
            //textViewSetValue("Sai Saree House");
            // Called when the reply has a fallback type.
        });
        chatAction.addOnNormalReplyFoundForListener(input -> {
            // Called when the reply has a normal type.
            ResponseViewSetValue("give in 10 bullet short points " + input);
            //textViewSetValue("Sai Saree House");
        });
        chatAction.addOnNoReplyFoundForListener(input -> {
            // Called when no reply was found for the input.
            ResponseViewSetValue("give in 15 short points " + input);
            //textViewSetValue("Sai Saree House");
        });


        // Set up a listener for a chat variable
        QiChatVariable nameVariable = qiChatbot.variable("Name");

        nameVariable.addOnValueChangedListener(
                currentValue -> {
                    Log.i(TAG, "Chat var Name: " + currentValue);
                    textViewSetValue("Hello " + currentValue);
                    ResponseViewSetValue("give in 2 short line " + currentValue);
                }
        );

        // Set up a listener for a chat variable
        QiChatVariable drinkVariable = qiChatbot.variable("Drink");

        drinkVariable.addOnValueChangedListener(
                currentValue -> {
                    Log.i(TAG, "Chat var Drink: " + currentValue);
                    imageViewSetValue(currentValue);
                }
        );





        // Create new say actions.
        sayAction1 = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("Hello") // Set the text to say. <, your friendly robot guide! let me show you around the Architecture Building and help you with any information you need regarding architecture department at kent state university>
                .build(); // Build the say action.

        sayAction2 = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("OK. I am ready to start.") // Set the text to say.
                .build(); // Build the say action.


        // Create animations
        Animation animation_good = AnimationBuilder.with(qiContext) // Create the builder with the context.
                .withResources(R.raw.nicereaction_a001) // Set the animation resource.
                .build(); // Build the animation.
        Animation animation_bad = AnimationBuilder.with(qiContext) // Create the builder with the context.
                .withResources(R.raw.sad_a001) // Set the animation resource.
                .build(); // Build the animation.

        Animation animation_handshake = AnimationBuilder.with(qiContext) // Create the builder with the context.
                .withResources(R.raw.shake_hands) // Set the animation resource.
                .build(); // Build the animation.

        Animation animation_pointscreen = AnimationBuilder.with(qiContext) // Create the builder with the context.
                .withResources(R.raw.point_table_screen) // Set the animation resource.
                .build(); // Build the animation.

        // Create animate actions
        goodAnimAction = AnimateBuilder.with(qiContext) // Create the builder with the context.
                .withAnimation(animation_good) // Set the animation.
                .build(); // Build the animate action.

        badAnimAction = AnimateBuilder.with(qiContext) // Create the builder with the context.
                .withAnimation(animation_bad) // Set the animation.
                .build(); // Build the animate action.

        handshakeAnimAction = AnimateBuilder.with(qiContext) // Create the builder with the context.
                .withAnimation(animation_handshake) // Set the animation.
                .build(); // Build the animate action.

        pointscreenAnimAction = AnimateBuilder.with(qiContext) // Create the builder with the context.
                .withAnimation(animation_pointscreen) // Set the animation.
                .build(); // Build the animate action.

        qiChatbot.addOnBookmarkReachedListener((bookmark) -> {
            Log.i(TAG, "Bookmark event: " + bookmark.getName());
            if (bookmark.getName().equals("turnon")) {
                runOnUiThread(() -> turnOn());
            }
            else if (bookmark.getName().equals("turnoff")) {

                runOnUiThread(() -> lightoff());

            }

            else if (bookmark.getName().equals("feelgood")) {
                goodAnimAction.async().run();
            }
            else if (bookmark.getName().equals("feelbad")) {

                badAnimAction.async().run();

            }
            else if (bookmark.getName().equals("ondisplay")) {

                pointscreenAnimAction.async().run();

            }
        });

    }

    @Override
    public void onRobotFocusLost() {
        this.qiContext = null;
        // Remove on started listeners from the Chat action.
        if (chatAction != null) {
            chatAction.removeAllOnStartedListeners();
        }
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        Log.e(TAG, "Focus refused because: " + reason);
    }

    @Override
    protected void onDestroy() {
        QiSDK.unregister(this, this);
        super.onDestroy();
    }
}




