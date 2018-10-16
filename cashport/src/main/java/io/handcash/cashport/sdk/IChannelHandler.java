package io.handcash.cashport.sdk;

import io.handcash.cashport.ISignTransactionRequestCallback;


public interface IChannelHandler {

    long WAIT_RESPONSE_TIMEOUT_MILLIS = 15000;

    void updateChannel(String channelId);

    void closeChannel();

    void registerTransactionRequestCallback(ISignTransactionRequestCallback signTransactionRequestCallback);
}
