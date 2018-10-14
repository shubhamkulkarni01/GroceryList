package sdhack.grocerylist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.camerakit.CameraKit;
import com.camerakit.CameraKitView;

public class Camera extends AppCompatActivity {

    private CameraKitView cameraKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraKitView = findViewById(R.id.camera);
        cameraKitView.setFocus(CameraKit.FOCUS_CONTINUOUS);

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

    }
