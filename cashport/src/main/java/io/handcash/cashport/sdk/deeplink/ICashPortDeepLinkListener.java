package io.handcash.cashport.sdk.deeplink;

import io.handcash.cashport.sdk.entity.AuthorizationResponse;

public interface ICashPortDeepLinkListener {

    void onSuccess(AuthorizationResponse authorizationResponse);

    void onDeny();

}