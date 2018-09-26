package io.handcash.cashport;

import io.handcash.cashport.sdk.entity.AuthorizationResponse;

public interface IRequestAuthorizationCallback {

    void onGrantAuthorization(AuthorizationResponse authorizationResponse);

    void onDenyAuthorization();

    void onInternalError(Throwable throwable);

}
