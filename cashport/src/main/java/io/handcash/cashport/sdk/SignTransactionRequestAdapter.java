package io.handcash.cashport.sdk;

import io.handcash.cashport.ISignTransactionRequestCallback;
import io.handcash.cashport.sdk.entity.CashportApiError;

public class SignTransactionRequestAdapter implements ISignTransactionRequestCallback {

    @Override
    public void onStart() {

    }

    @Override
    public void onEnd() {

    }

    @Override
    public void onSuccess(String transactionId) {

    }

    @Override
    public void onTokenExpired() {

    }

    @Override
    public void onAuthorizedFundsLimitReached() {

    }

    @Override
    public void onInsufficientWalletFunds() {

    }

    @Override
    public void onNotAuthorized() {

    }

    @Override
    public void onDeviceNotAvailable() {

    }

    @Override
    public void onWalletInternalError() {

    }

    @Override
    public void onBadRequest(String message, CashportApiError error) {

    }

    @Override
    public void onAPICallError(Throwable throwable) {

    }

}
