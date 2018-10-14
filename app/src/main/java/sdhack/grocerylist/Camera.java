package sdhack.grocerylist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.camerakit.CameraKit;
import com.camerakit.CameraKitView;

public class Camera extends AppCompatActivity implements View.OnClickListener, CameraKitView.ImageCallback {

    private CameraKitView cameraKitView;
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraKitView = findViewById(R.id.camera);
        cameraKitView.setFocus(CameraKit.FOCUS_CONTINUOUS);
        button = findViewById(R.id.imageButton);
        button.setOnClickListener(this);
    }

        @Override
        protected void onStart () {
            super.onStart();
            cameraKitView.onStart();
        }

        @Override
        protected void onResume () {
            super.onResume();
            cameraKitView.onResume();
        }

        @Override
        protected void onPause () {
            cameraKitView.onPause();
            super.onPause();
        }

        @Override
        protected void onStop () {
            cameraKitView.onStop();
            super.onStop();
        }

        @Override
        public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    @Override
    public void onClick(View v) {
        cameraKitView.captureImage(this);
    }

    @Override
    public void onImage(CameraKitView cameraKitView, byte[] bytes) {
        // Create a bitmap

    }
}
