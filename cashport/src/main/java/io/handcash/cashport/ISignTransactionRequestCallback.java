package io.handcash.cashport;

import io.handcash.cashport.sdk.entity.CashportApiError;

public interface ISignTransactionRequestCallback {

    void onStart();

    void onEnd();

    void onSuccess(String transactionId);

    void onTokenExpired();

    void onAuthorizedFundsLimitReached();

    void onInsufficientWalletFunds();

    void onNotAuthorized();

    void onDeviceNotAvailable();

    void onWalletInternalError();

    void onBadRequest(String message, CashportApiError error);

    void onAPICallError(Throwable throwable);
}
