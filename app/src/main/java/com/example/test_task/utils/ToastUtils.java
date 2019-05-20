package com.example.test_task.utils;

import android.widget.Toast;

import androidx.annotation.StringRes;

import com.example.test_task.App;

public class ToastUtils {

    private ToastUtils() {
        //empty
    }

    public static void showLong(@StringRes int resourceId) {
        showToast(App.getInstance().getString(resourceId), Toast.LENGTH_LONG);
    }

    public static void showLong(final String message) {
        showToast(message, Toast.LENGTH_LONG);
    }

    public static void showShort(int resourceId) {
        showToast(App.getInstance().getString(resourceId), Toast.LENGTH_SHORT);
    }

    public static void showShort(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    private static void showToast(final String message, final Integer length) {
        Toast.makeText(App.getInstance(), message, length).show();
    }
}