package com.example.annawang.meetyou.util;

import android.util.Log;

public class LogUtil {

    private static final boolean TRACE = true;
    public static final int DEFAULT_STACK_TRACE_LEVEL = 4;

    public static void trace() {
        trace(null, DEFAULT_STACK_TRACE_LEVEL);
    }

    public static void trace(String message) {
        trace(message, DEFAULT_STACK_TRACE_LEVEL);
    }

    public static void error(String message) {
        error(message, null, DEFAULT_STACK_TRACE_LEVEL);
    }

    public static void error(Throwable throwable) {
        error(null, throwable, DEFAULT_STACK_TRACE_LEVEL);
    }

    public static void error(String message, Throwable throwable) {
        error(message, throwable, DEFAULT_STACK_TRACE_LEVEL);
    }

    public static void trace(String message, int stackTracelevel) {
        if (TRACE) {
            String msg = message == null ? "" : " message: " + message;
            Log.d(Thread.currentThread().getStackTrace()[stackTracelevel].getClassName(), Thread.currentThread().getStackTrace()[stackTracelevel].toString() + msg);
        }
    }

    public static void error(String message, Throwable throwable, int stackTracelevel) {
        if (TRACE) {
            String msg = message == null ? "" : " message: " + message;
            Log.e(Thread.currentThread().getStackTrace()[stackTracelevel].getClassName(), Thread.currentThread().getStackTrace()[stackTracelevel].toString() + msg, throwable);
            CrashHandler.getInstance().saveCrashInfo2File(throwable);
        }
    }
}