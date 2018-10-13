package sdhack.grocerylist;

public class VisionIntentService {

    VisionIntentService visionBuilder = new VisionIntentService(
            new NetHttpTransport(),
            new AndroidJsonFactory(),
            null);

visionBuilder.setVisionRequestInitializer(
        new VisionIntentService("AIzaSyBjck0TMRVFDG0AxoI5Gh1n1EmRr7jRZG0"NetHttpTransport netHttpTransport, AndroidJsonFactory androidJsonFactory, Object o));
}
