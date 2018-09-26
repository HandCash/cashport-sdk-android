package io.handcash.cashport;

import io.handcash.cashport.sdk.entity.CashPortApiError;

public interface ISignTransactionRequestCallback {

    void onStart();

    void onEnd();

    void onSuccess();

    void onTokenExpired();

    void onAuthorizedFundsLimitReached();

    void onInsufficientWalletFunds();

    void onNotAuthorized();

    void onDeviceNotAvailable();

    void onInternalError();

    void onBadRequest(String message, CashPortApiError error);

    void onAPICallError(Throwable throwable);
}
