package io.handcash.cashport.sdk.pubnub;

import com.freaks.app.deeplink.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.lang.reflect.Type;

import io.handcash.cashport.ISignTransactionRequestCallback;
import io.handcash.cashport.sdk.SignTransactionRequestAdapter;
import io.handcash.cashport.sdk.SignTransactionResponseHandler;
import io.handcash.cashport.sdk.entity.CashportResponse;
import io.handcash.cashport.sdk.entity.SignTransactionResponseCode;
import io.handcash.cashport.util.Logger;


public class PubnubSubscribeCallback extends SubscribeCallback {

    private SignTransactionResponseHandler signTransactionResponseHandler;
    private Gson gson;

    public PubnubSubscribeCallback() {
        this.signTransactionResponseHandler = new SignTransactionResponseHandler(new SignTransactionRequestAdapter());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(SignTransactionResponseCode.class, new JsonDeserializer<SignTransactionResponseCode>() {

                    @Override
                    public SignTransactionResponseCode deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return SignTransactionResponseCode.getFromStatusCode(json.getAsInt());
                    }
                })
                .create();
    }

    public void setSignTransactionRequestCallback(ISignTransactionRequestCallback signTransactionRequestCallback) {
        this.signTransactionResponseHandler = new SignTransactionResponseHandler(signTransactionRequestCallback);
    }

    @Override
    public void status(PubNub pubnub, PNStatus status) {

    }

    @Override
    public void message(PubNub pubnub, PNMessageResult message) {
        try {
            String json = message.getMessage().toString().replace("\\", StringUtils.EMPTY);
            Logger.d(json);
            CashportResponse cashportResponse = gson.fromJson(json, CashportResponse.class);
            signTransactionResponseHandler.handleResponse(cashportResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void presence(PubNub pubnub, PNPresenceEventResult presence) {

    }

}
