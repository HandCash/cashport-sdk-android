package io.handcash.cashport.sdk.firebase;

import android.content.Context;
import android.content.IntentFilter;
import android.os.CountDownTimer;

import com.google.firebase.messaging.FirebaseMessaging;

import io.handcash.cashport.ISignTransactionRequestCallback;
import io.handcash.cashport.SignTransactionRequestWrapper;

public class FirebaseChannelHandler {

    private static final long WAIT_FIREBASE_RESPONSE_TIMEOUT_MILLIS = 15000;

    private Context context;

    private SendTransactionResponseReceiver sendTransactionResponseReceiver;
    private String currentChannel;

    public FirebaseChannelHandler(Context context) {
        this.context = context;
        this.sendTransactionResponseReceiver = new SendTransactionResponseReceiver();
        registerReceiver();
    }

    public void updateChannel(String channelId) {
        this.currentChannel = channelId;
        FirebaseMessaging.getInstance().subscribeToTopic( currentChannel );
    }

    public void closeChannel() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic( currentChannel );
        unregisterReceiver();
    }

    public void registerTransactionRequestCallback(ISignTransactionRequestCallback signTransactionRequestCallback) {
        final boolean[] hasPendingRequest = {true};
        this.sendTransactionResponseReceiver.setSignTransactionRequestCallback(
                new SignTransactionRequestWrapper( signTransactionRequestCallback ) {
                    @Override
                    public void onEnd() {
                        super.onEnd();
                        hasPendingRequest[0] = false;
                    }
                } );
        new CountDownTimer( WAIT_FIREBASE_RESPONSE_TIMEOUT_MILLIS, WAIT_FIREBASE_RESPONSE_TIMEOUT_MILLIS ) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if ( hasPendingRequest[0] ) {
                    signTransactionRequestCallback.onDeviceNotAvailable();
                }
            }
        }.start();
    }

    private void registerReceiver() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction( SendTransactionResponseIntent.ACTION );
            context.registerReceiver( sendTransactionResponseReceiver, intentFilter );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    private void unregisterReceiver() {
        if ( sendTransactionResponseReceiver != null ) {
            context.unregisterReceiver( sendTransactionResponseReceiver );
        }
    }

}
