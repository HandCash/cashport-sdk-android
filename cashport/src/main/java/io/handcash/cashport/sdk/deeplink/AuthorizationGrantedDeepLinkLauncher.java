package io.handcash.cashport.sdk.deeplink;

import com.freaks.app.deeplink.DeepLink;
import com.freaks.app.deeplink.IDeepLinkLauncher;

import io.handcash.cashport.sdk.entity.AuthorizationResponse;

public abstract class AuthorizationGrantedDeepLinkLauncher implements IDeepLinkLauncher {

    @Override
    public void launchDeepLink(DeepLink deepLink) {
        AuthorizationResponse authorizationResponse = new AuthorizationResponse();
        authorizationResponse.setAuthorizationToken( deepLink.getQueryParameters().get( "auth_token" ) );
        authorizationResponse.setUserHandle( deepLink.getQueryParameters().get( "handle" ) );
        authorizationResponse.setChannelId( deepLink.getQueryParameters().get( "channel_id" ) );
        authorizationResponse.setExpirationTimestamp( Long.valueOf( deepLink.getQueryParameters().get( "expiration_timestamp" ) ) );
        onLaunch( authorizationResponse );
    }

    public abstract void onLaunch(AuthorizationResponse authorizationResponse);

}
