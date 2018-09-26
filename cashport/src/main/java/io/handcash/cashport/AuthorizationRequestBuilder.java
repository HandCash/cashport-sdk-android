package io.handcash.cashport;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.handcash.cashport.sdk.entity.AuthorizationRequest;
import io.handcash.cashport.sdk.entity.PersonalInfoPermission;


public class AuthorizationRequestBuilder {

    private String appId;
    private List<PersonalInfoPermission> permissions;

    public static AuthorizationRequestBuilder withAppId(String appId) {
        return new AuthorizationRequestBuilder( appId );
    }

    public AuthorizationRequestBuilder(String appId) {
        this.appId = appId;
        this.permissions = new LinkedList<>();
    }

    public AuthorizationRequestBuilder addPermission(PersonalInfoPermission permission) {
        permissions.add( permission );
        return this;
    }

    public AuthorizationRequestBuilder addPermissions(PersonalInfoPermission... permissions) {
        this.permissions.addAll( Arrays.asList( permissions ) );
        return this;
    }

    public AuthorizationRequest build() {
        return new AuthorizationRequest( appId, permissions );
    }

}
