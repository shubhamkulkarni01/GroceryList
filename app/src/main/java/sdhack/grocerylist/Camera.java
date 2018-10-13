package sdhack.grocerylist;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Size;

public class Camera {




















    CameraManager cameraManager;
    Size size;
    String cameraId;
    cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

    try {
        String[] cameraIds = cameraManager.getCameraIdList();

        for(String cameraId : cameraIds) {
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);

            if(cameraCharacteristics.get(cameraCharacteristics.LENS_FACING) == CameraMetadata.LENS_FACING_FRONT) {
                this.cameraId = cameraId;
                StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                size = streamConfigurationMap.getOutputSizes(SurfaceTexture.class) [0];

            }
    }
    catch(CameraAccessException e) {
        e.printStackTrace();
    }


    }
