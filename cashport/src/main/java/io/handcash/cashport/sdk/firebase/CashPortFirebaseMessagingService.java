package io.handcash.cashport.sdk.firebase;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

public class CashPortFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d( "MessagingService", remoteMessage.getData().toString() );
        try {
            if ( !remoteMessage.getData().isEmpty() ) {
                handleAction( remoteMessage );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    private void handleAction(RemoteMessage remoteMessage) {
        try {
            Intent broadCastIntent = SendTransactionResponseIntent.fromRemoteMessage( remoteMessage );
            sendBroadcast( broadCastIntent );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

}