package io.handcash.cashport.sdk;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

import io.handcash.cashport.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComponentsFactory {

    public static ComponentsFactory create() {
        return new ComponentsFactory();
    }

    public Retrofit getCashPortRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.CASHPORT_API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PubNub getPubnub() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(BuildConfig.PUBNUB_SUBSCRIBE_KEY);

        return new PubNub(pnConfiguration);
    }
}
