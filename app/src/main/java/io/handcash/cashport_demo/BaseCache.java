package io.handcash.cashport_demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Date;
import java.util.Map;

import io.handcash.cashport.util.Logger;

public class BaseCache {

    private final String APPLICATION_CACHE_SHARED_PREFERENCE = "APPLICATION_PREFERENCES";

    protected Context context;
    protected SharedPreferences preferences;

    public BaseCache(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences( APPLICATION_CACHE_SHARED_PREFERENCE, Context.MODE_PRIVATE );
    }

    public Context getContext() {
        return this.context;
    }

    public void cleanCache() {
        Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public void removeKey(String key) {
        Editor editor = preferences.edit();
        editor.remove( key );
        editor.apply();
    }

    public void saveString(String key, String value) {
        if ( value == null ) {
            removeKey( key );
            return;
        }
        Editor editor = preferences.edit();
        editor.putString( key, value );
        editor.apply();
    }


    public String getString(String key) {
        return preferences.getString( key, "" );
    }


    public void saveInt(String key, Integer value) {
        if ( value == null ) {
            removeKey( key );
            return;
        }
        Editor editor = preferences.edit();
        editor.putInt( key, value );
        editor.apply();
    }

    public int getInt(String key) {
        return preferences.getInt( key, Integer.MIN_VALUE );
    }

    public void saveBoolean(String key, Boolean value) {
        if ( value == null ) {
            removeKey( key );
            return;
        }
        Editor editor = preferences.edit();
        editor.putBoolean( key, value );
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean( key, defaultValue );
    }

    public void saveLong(String key, Long value) {
        if ( value == null ) {
            removeKey( key );
            return;
        }
        Editor editor = preferences.edit();
        editor.putLong( key, value );
        editor.apply();
    }

    public long getLong(String key) {
        return preferences.getLong( key, Long.MIN_VALUE );
    }

    public void saveFloat(String key, Float value) {
        if ( value == null ) {
            removeKey( key );
            return;
        }
        Editor editor = preferences.edit();
        editor.putFloat( key, value );
        editor.apply();
    }

    public float getFloat(String key) {
        return preferences.getFloat( key, Float.MIN_VALUE );
    }

    public void saveDate(String key, Date value) {
        if ( value == null ) {
            removeKey( key );
            return;
        }
        Editor editor = preferences.edit();
        editor.putLong( key, value.getTime() );
        editor.apply();
    }


    public Date getDate(String key) {
        return new Date( preferences.getLong( key, 0L ) );
    }

    public void printCache() {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> preferencesMap = (Map<String, Object>) preferences.getAll();
        for ( String key : preferencesMap.keySet() ) {
            builder.append( key + ": " + preferencesMap.get( key ) + "\n" );
        }

        Logger.d( builder.toString() );

    }

}