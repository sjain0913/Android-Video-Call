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

    private static final String APPLICATION_ID = "84455";
    private static final String AUTH_KEY = "egJ2uUcMtw--XEk";
    private static final String AUTH_SECRET = "QfRY4DWpgdDrnp5";
    private static final String ACCOUNT_KEY = "12ioYPL-x4AcumLv3Ket";
    public static final String USER_DEFAULT_PASSWORD = "quickblox";
    private static VideoCall instance;
    private QBResRequestExecutor qbResRequestExecutor;

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

        // Uncomment and put your Api and Chat servers endpoints if you want to point the sample
        // against your own server.
        //
        // QBSettings.getInstance().setEndpoints("https://your_api_endpoint.com", "your_chat_endpoint", ServiceZone.PRODUCTION);
        // QBSettings.getInstance().setZone(ServiceZone.PRODUCTION);
    }

    public synchronized QBResRequestExecutor getQbResRequestExecutor() {
        return qbResRequestExecutor == null
                ? qbResRequestExecutor = new QBResRequestExecutor()
                : qbResRequestExecutor;
    }

    public static VideoCall getInstance() {
        return instance;
    }

}