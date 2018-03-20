package com.example.annawang.meetyou.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionUtil {

    public static boolean checkHavePermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(context
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    public static void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity
                , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                , 0);
    }
}
