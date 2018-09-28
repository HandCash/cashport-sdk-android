package io.handcash.cashport.sdk.entity;

public class AuthorizationConfiguration {

    private String scheme;
    private String host;
    private String successPath;
    private String deniedPath;

    public AuthorizationConfiguration() {
    }

    public AuthorizationConfiguration(String scheme, String host, String successPath, String deniedPath) {
        this.scheme = scheme;
        this.host = host;
        this.successPath = successPath;
        this.deniedPath = deniedPath;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSuccessPath() {
        return successPath;
    }

    public void setSuccessPath(String successPath) {
        this.successPath = successPath;
    }

    public String getDeniedPath() {
        return deniedPath;
    }

    public void setDeniedPath(String deniedPath) {
        this.deniedPath = deniedPath;
    }

    @Override
    public String toString() {
        return "AuthorizationConfiguration{" +
                ", scheme='" + scheme + '\'' +
                ", host='" + host + '\'' +
                ", successPath='" + successPath + '\'' +
                ", deniedPath='" + deniedPath + '\'' +
                '}';
    }
}
