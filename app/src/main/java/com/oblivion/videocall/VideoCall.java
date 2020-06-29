package com.oblivion.videocall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;

import com.crashlytics.android.BuildConfig;
import com.crashlytics.android.Crashlytics;
import com.quickblox.auth.session.QBSettings;
import com.oblivion.videocall.util.QBResRequestExecutor;

import io.fabric.sdk.android.Fabric;

public class VideoCall extends Application {

    private static final String APPLICATION_ID = "84375";
    private static final String AUTH_KEY = "Xd5G-pJy-pGASbz";
    private static final String AUTH_SECRET = "nRLwDJ87T-7n98E";
    private static final String ACCOUNT_KEY = "12ioYPL-x4AcumLv3Ket";
    private static VideoCall instance;
    private QBResRequestExecutor qbResRequestExecutor;
    private static String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,

            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE
    };

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initFabric();
        checkAppCredentials();
        initCredentials();
    }

    private void initFabric() {
        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
    }

    private void checkAppCredentials() {
        if (APPLICATION_ID.isEmpty() || AUTH_KEY.isEmpty() || AUTH_SECRET.isEmpty() || ACCOUNT_KEY.isEmpty()) {
            throw new AssertionError(getString(R.string.error_credentials_empty));
        }
    }

    private void initCredentials() {
        QBSettings.getInstance().init(getApplicationContext(), APPLICATION_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);
    }

    public synchronized QBResRequestExecutor getQbResRequestExecutor() {
        return qbResRequestExecutor == null
                ? qbResRequestExecutor = new QBResRequestExecutor()
                : qbResRequestExecutor;
    }

    public static VideoCall getInstance() {
        return instance;
    }


//    /**
//     * Checks if the app has the required permissions
//     * If the app does not has permission then the user will be prompted to grant permissions
//     * @param activity
//     */
//    public static void verifyPermissions(Activity activity) {
//        for (int i = 0; i < PERMISSIONS.length; i++) {
//            int permission = ActivityCompat.checkSelfPermission(activity, PERMISSIONS[i]);
//
//            if (permission != PackageManager.PERMISSION_GRANTED) {
//                // We don't have permission so prompt the user
//                ActivityCompat.requestPermissions(
//                        activity,
//                        PERMISSIONS,
//                        1
//                );
//            }
//        }
//    }
}