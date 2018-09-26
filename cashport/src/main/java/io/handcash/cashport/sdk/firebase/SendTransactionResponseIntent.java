package io.handcash.cashport.sdk.firebase;

import android.content.Intent;

import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.Map;

import io.handcash.cashport.sdk.entity.SignTransactionResponse;
import io.handcash.cashport.sdk.entity.SignTransactionResponseCode;


public class SendTransactionResponseIntent {

    public static final String ACTION = "ACTION_SEND_TX_RESPONSE";
    public static final String EXTRA_SIGN_TRANSACTION_RESPONSE = "signTransactionResponse";

    public static Intent fromRemoteMessage(RemoteMessage remoteMessage) throws JSONException {
        SignTransactionResponse signTransactionResponse = getSignTransactionResponse( remoteMessage );

        Intent intent = new Intent( ACTION );
        intent.putExtra( EXTRA_SIGN_TRANSACTION_RESPONSE, signTransactionResponse );
        return intent;
    }

    public static SignTransactionResponse getSignTransactionResponse(RemoteMessage remoteMessage) {
        int statusCode = Integer.parseInt( remoteMessage.getData().get( "statusCode" ) );
        String developerMessage = remoteMessage.getData().get( "developerMessage" );
        String jsonParameters = remoteMessage.getData().get( "parameters" );
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> parameters = new GsonBuilder().create().fromJson( jsonParameters, mapType );

        SignTransactionResponse signTransactionResponse = new SignTransactionResponse();
        signTransactionResponse.setStatusCode( SignTransactionResponseCode.getFromStatusCode( statusCode ) );
        signTransactionResponse.setDeveloperMessage( developerMessage );
        signTransactionResponse.setParameters( parameters );
        return signTransactionResponse;
    }

}
