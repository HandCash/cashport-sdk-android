package io.handcash.cashport.sdk.entity;

public class AuthorizationResponse {

    private String authorizationToken;
    private String userHandle;
    private String channelId;
    private long expirationTimestamp;

    public AuthorizationResponse() {
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public long getExpirationTimestamp() {
        return expirationTimestamp;
    }

    public void setExpirationTimestamp(long expirationTimestamp) {
        this.expirationTimestamp = expirationTimestamp;
    }

    @Override
    public String toString() {
        return "AuthorizationResponse{" +
                "authorizationToken='" + authorizationToken + '\'' +
                ", userHandle='" + userHandle + '\'' +
                ", channelId='" + channelId + '\'' +
                ", expirationTimestamp=" + expirationTimestamp +
                '}';
    }
}
