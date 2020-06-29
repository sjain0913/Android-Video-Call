package com.oblivion.videocall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class VideoCall extends AppCompatActivity {

    private static final String APPLICATION_ID = "84375";
    private static final String AUTH_KEY = "Xd5G-pJy-pGASbz";
    private static final String AUTH_SECRET = "nRLwDJ87T-7n98E";
    private static final String ACCOUNT_KEY = "12ioYPL-x4AcumLv3Ket";
    private static VideoCall instance;
    private static String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // asking and verifying permissions needed
        verifyPermissions(this);
    }


    /**
     * Checks if the app has the required permissions
     * If the app does not has permission then the user will be prompted to grant permissions
     * @param activity
     */
    public static void verifyPermissions(Activity activity) {
        for (int i = 0; i < PERMISSIONS.length; i++) {
            int permission = ActivityCompat.checkSelfPermission(activity, PERMISSIONS[i]);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        activity,
                        PERMISSIONS,
                        1
                );
            }
        }
    }
}