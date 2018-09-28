package io.handcash.cashport;

import io.handcash.cashport.sdk.entity.AuthorizationConfiguration;


public class AuthorizationConfigurationBuilder {

    private String scheme;
    private String host;
    private String successPath;
    private String deniedPath;

    public static AuthorizationConfigurationBuilder create() {
        return new AuthorizationConfigurationBuilder();
    }

    public AuthorizationConfigurationBuilder setScheme(String scheme) {
        this.scheme = scheme;
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
        return new AuthorizationConfiguration(scheme, host, successPath, deniedPath );
    }

}
