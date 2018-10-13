package sdhack.grocerylist;

import android.app.IntentService;
import android.content.Intent;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class VisionIntentService extends IntentService {
    public VisionIntentService(String name) {
        super(name);
    }

    private static final String TARGET_URL =
            "https://vision.googleapis.com/v1/images:annotate?";
    private static final String API_KEY =
            "key=AIzaSyBjck0TMRVFDG0AxoI5Gh1n1EmRr7jRZG0";

    @Override
    protected void onHandleIntent( Intent intent) {

        URL serverUrl = new URL(TARGET_URL + API_KEY);
        URLConnection urlConnection = serverUrl.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setDoOutput(true);

        String response = httpConnection.getResponseMessage();

    }
}
