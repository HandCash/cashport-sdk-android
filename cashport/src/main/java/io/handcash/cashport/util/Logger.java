package io.handcash.cashport.util;

import android.app.Activity;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class Logger {

    public enum LogMode {
        ALL,
        VERBOSE,
        DEBUG,
        WARNING,
        ERROR,
        QUIET
    }

    public static String LOG_TAG = "CASHPORT";
    public static LogMode LOG_MODE = LogMode.ALL;

    public static String buildMessage(Activity activity, String message) {
        return "[" + activity.getLocalClassName() + "] " + message;
    }

    public static String buildMessage(String message) {
        return message;
    }

    public static String buildMessage(Object obj, String message) {
        return "[" + obj.getClass().getSimpleName() + "] " + message;
    }

    public static void d(Object obj, String message) {
        if ( message == null )
            return;
        if ( LOG_MODE.ordinal() <= LogMode.DEBUG.ordinal() )
            Log.d( LOG_TAG, buildMessage( obj, message ) );
    }

    public static void d(Activity activity, String message) {
        if ( message == null )
            return;
        if ( LOG_MODE.ordinal() <= LogMode.DEBUG.ordinal() )
            Log.d( LOG_TAG, buildMessage( activity, message ) );
    }

    public static void d(String message) {
        if ( message == null )
            return;
        if ( LOG_MODE.ordinal() <= LogMode.DEBUG.ordinal() )
            Log.d( LOG_TAG, buildMessage( message ) );
    }


    public static void v(Object obj, String message) {
        if ( message == null )
            return;
        if ( LOG_MODE.ordinal() <= LogMode.VERBOSE.ordinal() )
            Log.v( LOG_TAG, buildMessage( obj, message ) );
    }

    public static void v(Activity activity, String message) {
        if ( message == null )
            return;
        if ( LOG_MODE.ordinal() <= LogMode.VERBOSE.ordinal() )
            Log.v( LOG_TAG, buildMessage( activity, message ) );
    }

    public static void v(String message) {
        if ( message == null )
            return;
        if ( LOG_MODE.ordinal() <= LogMode.VERBOSE.ordinal() )
            Log.v( LOG_TAG, buildMessage( message ) );
    }

    public static void w(Object obj, String message) {
        if ( message == null )
            return;
        if ( LOG_MODE.ordinal() <= LogMode.WARNING.ordinal() )
            Log.w( LOG_TAG, buildMessage( obj, message ) );
    }

    public static void w(Activity activity, String message) {
        if ( message == null )
            return;
        if ( LOG_MODE.ordinal() <= LogMode.WARNING.ordinal() )
            Log.w( LOG_TAG, buildMessage( activity, message ) );
    }

    public static void w(String message) {
        if ( message == null )
            return;
        if ( LOG_MODE.ordinal() <= LogMode.WARNING.ordinal() )
            Log.w( LOG_TAG, buildMessage( message ) );
    }

    public static void e(Activity activity, Throwable aThrowable) {
        if ( LOG_MODE.ordinal() <= LogMode.ERROR.ordinal() )
            Log.e( LOG_TAG, buildMessage( activity, getStackTrace( aThrowable ) ) );
    }

    public static void e(Object obj, Throwable aThrowable) {
        if ( LOG_MODE.ordinal() <= LogMode.ERROR.ordinal() )
            Log.e( LOG_TAG, buildMessage( obj, getStackTrace( aThrowable ) ) );
    }

    public static void e(Throwable aThrowable) {
        if ( LOG_MODE.ordinal() <= LogMode.ERROR.ordinal() )
            Log.e( LOG_TAG, buildMessage( getStackTrace( aThrowable ) ) );
    }

    public static void e(String message) {
        if ( message == null )
            return;
        if ( LOG_MODE.ordinal() <= LogMode.ERROR.ordinal() )
            Log.e( LOG_TAG, buildMessage( message ) );
    }

    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter( result );
        aThrowable.printStackTrace( printWriter );
        return result.toString();
    }

}