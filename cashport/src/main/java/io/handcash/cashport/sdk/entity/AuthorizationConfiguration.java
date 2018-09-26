package io.handcash.cashport.sdk.entity;

public class AuthorizationConfiguration {

    private String schema;
    private String host;
    private String successPath;
    private String deniedPath;

    public AuthorizationConfiguration() {
    }

    public AuthorizationConfiguration(String schema, String host, String successPath, String deniedPath) {
        this.schema = schema;
        this.host = host;
        this.successPath = successPath;
        this.deniedPath = deniedPath;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
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
                ", schema='" + schema + '\'' +
                ", host='" + host + '\'' +
                ", successPath='" + successPath + '\'' +
                ", deniedPath='" + deniedPath + '\'' +
                '}';
    }
}
