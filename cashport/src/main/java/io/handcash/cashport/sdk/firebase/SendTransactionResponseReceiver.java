package io.handcash.cashport.sdk.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import io.handcash.cashport.SignTransactionRequestAdapter;
import io.handcash.cashport.util.Logger;
import io.handcash.cashport.ISignTransactionRequestCallback;
import io.handcash.cashport.sdk.entity.SignTransactionResponse;

public class SendTransactionResponseReceiver extends BroadcastReceiver {

    private ISignTransactionRequestCallback signTransactionRequestCallback;

    public SendTransactionResponseReceiver() {
        this.signTransactionRequestCallback = new SignTransactionRequestAdapter();
    }

    public void setSignTransactionRequestCallback(ISignTransactionRequestCallback signTransactionRequestCallback) {
        this.signTransactionRequestCallback = signTransactionRequestCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SignTransactionResponse signTransactionResponse = (SignTransactionResponse) intent.getSerializableExtra(
                SendTransactionResponseIntent.EXTRA_SIGN_TRANSACTION_RESPONSE );
        Logger.d( "Handling response: " + signTransactionResponse );
        handleResponse( signTransactionResponse );
    }

    private void handleResponse(SignTransactionResponse signTransactionResponse) {
        signTransactionRequestCallback.onEnd();
        switch ( signTransactionResponse.getStatusCode() ) {
            case ACCEPTED:
                signTransactionRequestCallback.onSuccess();
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
                signTransactionRequestCallback.onInternalError();
                break;
            case INSUFFICIENT_FUNDS:
                signTransactionRequestCallback.onInsufficientWalletFunds();
                break;
        }
    }

}
