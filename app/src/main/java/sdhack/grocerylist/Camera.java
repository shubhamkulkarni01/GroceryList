package sdhack.grocerylist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;

import com.camerakit.CameraKitView;

public class Camera extends AppCompatActivity {

    private CameraKitView cameraKitView;
    private ImageButton button;

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("class", "camera");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("class", "camera");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            Log.d("started", "the camera");
            startActivityForResult(takePictureIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("IMAGE", "we got an image!!");
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Intent intent = new Intent(this, VisionIntentService.class);
            intent.putExtra("IMAGE", imageBitmap);
            Log.d("starting service", "starting service!!!");
            startService(intent);
        }
    }
/*
    @Override
    public void onImage(CameraKitView cameraKitView, byte[] bytes) {
        // Create a bitmap
        Log.d("Took a picture", "WE JUST TOOK A PICTURE");
        Intent intent = new Intent(this, VisionIntentService.class);
        intent.putExtra("IMAGE", bytes);
        startService(intent);
    }*/
}
