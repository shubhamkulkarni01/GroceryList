package sdhack.grocerylist;

import android.app.IntentService;
import android.content.Intent;

public class VisionIntentService implements IntentService {




    @Override
    protected void onHandleIntent(@androidx.annotation.Nullable Intent intent) {

        VisionIntentService visionBuilder = new VisionIntentService(
                new NetHttpTransport(),
                new AndroidJsonFactory(),
                null);

        new VisionIntentService("AIzaSyBjck0TMRVFDG0AxoI5Gh1n1EmRr7jRZG0", netHttpTransport, AndroidJsonFactory androidJsonFactory, Object o);
        visionBuilder.setVisionRequestInitializer();
        );
    }
}
