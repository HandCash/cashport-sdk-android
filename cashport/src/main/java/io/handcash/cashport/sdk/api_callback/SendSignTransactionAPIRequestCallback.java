package io.handcash.cashport.sdk.api_callback;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import io.handcash.cashport.ISignTransactionRequestCallback;
import io.handcash.cashport.sdk.entity.CashPortApiError;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendSignTransactionAPIRequestCallback implements Callback<Void> {

    private static final String ERROR_MESSAGE_KEY = "errorMessage";
    private static final String ERROR_CODE_KEY = "errorCode";

    private ISignTransactionRequestCallback callback;

    public SendSignTransactionAPIRequestCallback(ISignTransactionRequestCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(@NonNull Call<Void> call,
                           @NonNull Response<Void> response) {
        if ( response.isSuccessful() ) {
            // Wait to Firebase to get a response!
        } else {
            callback.onEnd();
            try {
                JSONObject jsonError = new JSONObject( response.errorBody().string() );
                String errorMessage = jsonError.getString( ERROR_MESSAGE_KEY );
                int errorCode = jsonError.getInt( ERROR_CODE_KEY );
                callback.onBadRequest( errorMessage, CashPortApiError.getFromCode( errorCode ) );
            } catch ( Exception e ) {
                callback.onAPICallError( e );
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<Void> call,
                          @NonNull Throwable t) {
        t.printStackTrace();
        callback.onEnd();
        callback.onAPICallError( t );
    }
}
