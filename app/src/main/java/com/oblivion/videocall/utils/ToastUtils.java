package com.oblivion.videocall.utils;


import android.widget.Toast;

import com.oblivion.videocall.VideoCall;

import androidx.annotation.StringRes;

public class ToastUtils {

    private ToastUtils() {
        //empty
    }

    public static void shortToast(String message) {
        show(message, Toast.LENGTH_LONG);
    }

    public static void shortToast(@StringRes int resource) {
        show(VideoCall.getInstance().getString(resource), Toast.LENGTH_SHORT);
    }

    public static void longToast(String message) {
        show(message, Toast.LENGTH_LONG);
    }

    public static void longToast(@StringRes int resource) {
        show(VideoCall.getInstance().getString(resource), Toast.LENGTH_LONG);
    }

    private static void show(String message, int length) {
        Toast.makeText(VideoCall.getInstance(), message, length).show();
    }
}