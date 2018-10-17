package io.handcash.cashport.sdk.pubnub;

import android.os.CountDownTimer;

import com.pubnub.api.PubNub;

import java.util.Collections;

import io.handcash.cashport.ISignTransactionRequestCallback;
import io.handcash.cashport.sdk.ComponentsFactory;
import io.handcash.cashport.sdk.IChannelHandler;
import io.handcash.cashport.sdk.SignTransactionRequestWrapper;

public class PubnubChannelHandler implements IChannelHandler {

    private PubNub pubNub;
    private String currentChannel;
    private PubnubSubscribeCallback pubnubSubscribeCallback;

    public PubnubChannelHandler() {
        this.pubNub = ComponentsFactory.create().getPubnub();
        this.pubnubSubscribeCallback = new PubnubSubscribeCallback();
        this.pubNub.addListener(this.pubnubSubscribeCallback);
    }

    @Override
    public void updateChannel(String channelId) {
        this.currentChannel = channelId;
        pubNub.unsubscribeAll();
        pubNub.subscribe()
                .channels(Collections.singletonList(currentChannel))
                .execute();
    }

    @Override
    public void closeChannel() {
        pubNub.unsubscribeAll();
    }

    @Override
    public void registerTransactionRequestCallback(ISignTransactionRequestCallback signTransactionRequestCallback) {
        final boolean[] hasReceivedResponse = {false};
        pubnubSubscribeCallback.setSignTransactionRequestCallback(
                new SignTransactionRequestWrapper(signTransactionRequestCallback) {
                    @Override
                    public void onEnd() {
                        super.onEnd();
                        hasReceivedResponse[0] = true;
                    }
                });
        new CountDownTimer(IChannelHandler.WAIT_RESPONSE_TIMEOUT_MILLIS, IChannelHandler.WAIT_RESPONSE_TIMEOUT_MILLIS) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (!hasReceivedResponse[0]) {
                    signTransactionRequestCallback.onDeviceNotAvailable();
                }
            }
        }.start();
    }

}
