package sdhack.grocerylist;

import android.app.IntentService;
import android.content.Intent;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

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

        URL serverUrl = null;
        try {
            serverUrl = new URL(TARGET_URL + API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection urlConnection = null;
        try {
            urlConnection = serverUrl.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;

        try {
            httpConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setDoOutput(true);

        BufferedWriter httpRequestBodyWriter = null;
        try {
            httpRequestBodyWriter = new BufferedWriter(new
                    OutputStreamWriter(httpConnection.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpRequestBodyWriter.write
                    ("{\"requests\":  [{ \"features\":  [ {\"type\": \"LABEL_DETECTION\""
                            +"}], \"image\": {\"source\": { \"gcsImageUri\":"
                            +" \"gs://vision-sample-images/4_Kittens.jpg\"}}}]}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpRequestBodyWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String response = httpConnection.getResponseMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (httpConnection.getInputStream() == null) {
                System.out.println("No stream");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner httpResponseScanner = null;
        try {
            httpResponseScanner = new Scanner(httpConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resp = "";
        while (httpResponseScanner.hasNext()) {
            String line = httpResponseScanner.nextLine();
            resp += line;
            System.out.println(line);  //  alternatively, print the line of response
        }
        httpResponseScanner.close();
    }
}
