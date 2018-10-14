package sdhack.grocerylist;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class VisionIntentService extends IntentService{
    public VisionIntentService(String name) {
        super(name);
    }
    public VisionIntentService(){
        super("VisionIntentService");
    }

    private BatchAnnotateImagesResponse response = null;

    private static final String TARGET_URL =
            "https://vision.googleapis.com/v1/images:annotate?";
    private static final String API_KEY =
            "key=AIzaSyBjck0TMRVFDG0AxoI5Gh1n1EmRr7jRZG0";
    private static final String CLOUD_VISION_API_KEY = "AIzaSyBjck0TMRVFDG0AxoI5Gh1n1EmRr7jRZG0";

    private Bitmap bitmap = null;

    ResultReceiver m;

    @Override
    protected void onHandleIntent( Intent intent) {
        Log.i("IMAGE", "an image has arrived");

        bitmap = intent.getParcelableExtra("IMAGE");
        m = (ResultReceiver)intent.getParcelableExtra("reply");
        callCloudVision(bitmap);

        /*
        try {
            URL serverUrl = new URL(TARGET_URL + API_KEY);
            URLConnection urlConnection = serverUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;

            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setDoOutput(true);

            BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                    OutputStreamWriter(httpConnection.getOutputStream()));
            httpRequestBodyWriter.write
                    ("{\"requests\":  [{ \"features\":  [ {\"type\": \"LABEL_DETECTION\""
                            + "}], \"image\": {\"source\": { \"gcsImageUri\":"
                            + " \"gs://vision-sample-images/4_Kittens.jpg\"}}}]}");
            httpRequestBodyWriter.close();

            String response = httpConnection.getResponseMessage();

            if (httpConnection.getInputStream() == null) {
                System.out.println("No stream");
                return;
            }

            Scanner httpResponseScanner = new Scanner(httpConnection.getInputStream());
            String resp = "";
            while (httpResponseScanner.hasNext()) {
                String line = httpResponseScanner.nextLine();
                resp += line;
                System.out.println(line);  //  alternatively, print the line of response
            }
            httpResponseScanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }*/



    }


    private Vision.Images.Annotate prepareAnnotationRequest(final Bitmap bitmap) throws IOException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        VisionRequestInitializer requestInitializer =
                new VisionRequestInitializer(CLOUD_VISION_API_KEY);

        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        builder.setVisionRequestInitializer(requestInitializer);

        Vision vision = builder.build();

        BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                new BatchAnnotateImagesRequest();
        batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

            // Add the image
            Image base64EncodedImage = new Image();
            // Convert the bitmap to a JPEG
            // Just in case it's a format that Android understands but Cloud Vision
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Base64 encode the JPEG
            base64EncodedImage.encodeContent(imageBytes);
            annotateImageRequest.setImage(base64EncodedImage);

            // add the features we want
            annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                Feature labelDetection = new Feature();
                labelDetection.setType("LABEL_DETECTION");
                labelDetection.setMaxResults(10);
                add(labelDetection);
            }});

            // Add the list of one thing to the request
            add(annotateImageRequest);
        }});

        Vision.Images.Annotate annotateRequest =
                vision.images().annotate(batchAnnotateImagesRequest);
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotateRequest.setDisableGZipContent(true);
        Log.d(TAG, "created Cloud Vision request object, sending request");

        return annotateRequest;
    }
    protected String runRequest(Bitmap bitmap) {
            try {
                Log.d(TAG, "created Cloud Vision request object, sending request");
                response = prepareAnnotationRequest(bitmap).execute();
                ArrayList<String> description = new ArrayList<>();
                for (int i = 0; i < response.getResponses().size(); i++)
                    for (EntityAnnotation annotation : response.getResponses().get(0).getLabelAnnotations())
                        description.add(annotation.getDescription());
                Bundle b = new Bundle();
                b.putSerializable("",description);
                m.send(0, b);
                Log.d(TAG, response.getResponses().get(0).getLabelAnnotations().toString());
                return "Success";
            } catch (GoogleJsonResponseException e) {
                Log.d(TAG, "failed to make API request because " + e.getContent());
            } catch (IOException e) {
                Log.d(TAG, "failed to make API request because of other IOException " +
                        e.getMessage());
            }
            return "Cloud Vision API request failed. Check logs for details.";
        }

    private void callCloudVision(final Bitmap bitmap) {
        try {
            runRequest(bitmap);
        } catch (Exception e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
    }

}