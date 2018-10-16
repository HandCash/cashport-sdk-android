package io.handcash.cashport.sdk;


import android.os.Handler;
import android.os.Looper;

import io.handcash.cashport.ISignTransactionRequestCallback;
import io.handcash.cashport.sdk.entity.CashportResponse;

public class SignTransactionResponseHandler {

    private ISignTransactionRequestCallback signTransactionRequestCallback;

    public SignTransactionResponseHandler(ISignTransactionRequestCallback signTransactionRequestCallback) {
        this.signTransactionRequestCallback = signTransactionRequestCallback;
    }

    public void handleResponse(CashportResponse cashportResponse) {
        new Handler(Looper.getMainLooper()).post(() -> {
            signTransactionRequestCallback.onEnd();
            switch (cashportResponse.getStatusCode()) {
                case ACCEPTED:
                    String txId = cashportResponse.getParameters().get("transactionId");
                    signTransactionRequestCallback.onSuccess(txId);
                    break;
                case NOT_AUTHORIZED:
                    signTransactionRequestCallback.onNotAuthorized();
                    break;
                case LIMIT_REACHED:
                    signTransactionRequestCallback.onAuthorizedFundsLimitReached();
                    break;
                case TOKEN_EXPIRED:
                    signTransactionRequestCallback.onTokenExpired();
                    break;
                case INTERNAL_ERROR:
                    signTransactionRequestCallback.onWalletInternalError();
                    break;
                case INSUFFICIENT_FUNDS:
                    signTransactionRequestCallback.onInsufficientWalletFunds();
                    break;
            }
        });
    }

}
