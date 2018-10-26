package io.handcash.cashport_demo;

import android.content.Context;

public class DemoAppCache extends BaseCache {

    private static final String AUTH_TOKEN_KEY = "auth_token";
    private static final String HANDLE_KEY = "handle_token";
    private static final String CHANNEL_ID_KEY = "channel_id";
    private static final String EXPIRATION_TIMESTAMP_KEY = "expiration_timestamp";

    public DemoAppCache(Context context) {
        super( context );
    }

    public void setAuthorizationToken(String authToken) {
        saveString( AUTH_TOKEN_KEY, authToken );
    }

    public String getAuthorizationToken() {
        return getString( AUTH_TOKEN_KEY );
    }

    public void setHandle(String handle) {
        saveString( HANDLE_KEY, handle );
    }

    public String getHandle() {
        return getString( HANDLE_KEY );
    }

    public void setChannelId(String channelId) {
        saveString( CHANNEL_ID_KEY, channelId );
    }

    public String getChannelId() {
        return getString( CHANNEL_ID_KEY );
    }

    public void setTokenExpirationTimestamp(long expirationTimestamp) {
        saveLong( EXPIRATION_TIMESTAMP_KEY, expirationTimestamp );
    }

    public long getTokenExpirationTimestamp() {
        return getLong( EXPIRATION_TIMESTAMP_KEY );
    }

}
