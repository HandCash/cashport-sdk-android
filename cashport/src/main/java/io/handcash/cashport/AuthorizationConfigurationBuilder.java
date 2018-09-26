package io.handcash.cashport;

import io.handcash.cashport.sdk.entity.AuthorizationConfiguration;


public class AuthorizationConfigurationBuilder {

    private String schema;
    private String host;
    private String successPath;
    private String deniedPath;

    public static AuthorizationConfigurationBuilder create() {
        return new AuthorizationConfigurationBuilder();
    }

    public AuthorizationConfigurationBuilder setSchema(String schema) {
        this.schema = schema;
        return this;
    }

    public AuthorizationConfigurationBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public AuthorizationConfigurationBuilder setSuccessPath(String successPath) {
        this.successPath = successPath;
        return this;
    }

    public AuthorizationConfigurationBuilder setDeniedPath(String deniedPath) {
        this.deniedPath = deniedPath;
        return this;
    }

    public AuthorizationConfiguration build() {
        return new AuthorizationConfiguration( schema, host, successPath, deniedPath );
    }

}
