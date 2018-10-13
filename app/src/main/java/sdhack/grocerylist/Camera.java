package sdhack.grocerylist;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.Size;

public abstract class Camera {

    CameraManager cameraManager;
    Size size;

    cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

    try {
        String[] cameraIds = cameraManager.getCameraIdList();

        for(String cameraID : cameraIds) {
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraID)
        }
    }
    catch(CameraAccessException e) {
        e.printStackTrace();
    }


    }
