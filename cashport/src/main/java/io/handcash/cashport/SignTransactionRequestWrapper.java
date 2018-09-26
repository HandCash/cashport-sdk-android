package io.handcash.cashport;

import io.handcash.cashport.sdk.entity.CashPortApiError;

public class SignTransactionRequestWrapper implements ISignTransactionRequestCallback {

    private ISignTransactionRequestCallback signTransactionRequestCallback;

    public SignTransactionRequestWrapper(ISignTransactionRequestCallback signTransactionRequestCallback) {
        this.signTransactionRequestCallback = signTransactionRequestCallback;
    }

    @Override
    public void onStart() {
        signTransactionRequestCallback.onStart();
    }

    @Override
    public void onEnd() {
        signTransactionRequestCallback.onEnd();
    }

    @Override
    public void onSuccess() {
        signTransactionRequestCallback.onSuccess();
    }

    @Override
    public void onTokenExpired() {
        signTransactionRequestCallback.onTokenExpired();
    }

    @Override
    public void onAuthorizedFundsLimitReached() {
        signTransactionRequestCallback.onAuthorizedFundsLimitReached();
    }

    @Override
    public void onInsufficientWalletFunds() {
        signTransactionRequestCallback.onInsufficientWalletFunds();
    }

    @Override
    public void onNotAuthorized() {
        signTransactionRequestCallback.onNotAuthorized();
    }

    @Override
    public void onDeviceNotAvailable() {
        signTransactionRequestCallback.onDeviceNotAvailable();
    }

    @Override
    public void onInternalError() {
        signTransactionRequestCallback.onInternalError();
    }

    @Override
    public void onBadRequest(String message, CashPortApiError error) {
        signTransactionRequestCallback.onBadRequest( message, error );
    }

    @Override
    public void onAPICallError(Throwable throwable) {
        signTransactionRequestCallback.onAPICallError( throwable );
    }

}
