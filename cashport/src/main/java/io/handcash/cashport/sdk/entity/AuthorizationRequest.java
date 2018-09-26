package io.handcash.cashport.sdk.entity;

import java.util.ArrayList;
import java.util.List;

public class AuthorizationRequest {

    private String appId;
    private List<PersonalInfoPermission> permissions;

    public AuthorizationRequest() {
    }

    public AuthorizationRequest(String appId, List<PersonalInfoPermission> permissions) {
        this.appId = appId;
        this.permissions = permissions;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<PersonalInfoPermission> getPermissions() {
        return permissions;
    }

    public List<String> getPermissionsAsQueryNames() {
        List<String> queryNames = new ArrayList<>();
        for ( PersonalInfoPermission permission : permissions ) {
            queryNames.add( permission.getQueryName() );
        }
        return queryNames;
    }

    public void setPermissions(List<PersonalInfoPermission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "AuthorizationRequest{" +
                "appId='" + appId + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
