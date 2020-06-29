package com.oblivion.videocall.utils;


import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.oblivion.videocall.VideoCall;

public class KeyboardUtils {

    private KeyboardUtils() {
    }

    public static void showKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) VideoCall.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hideKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) VideoCall.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}