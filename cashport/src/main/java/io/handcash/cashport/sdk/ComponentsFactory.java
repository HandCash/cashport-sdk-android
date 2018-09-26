package io.handcash.cashport.sdk;

import io.handcash.cashport.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComponentsFactory {

    public static ComponentsFactory create() {
        return new ComponentsFactory();
    }

    public Retrofit getCashPortRetrofit() {
        return new Retrofit.Builder()
                .baseUrl( BuildConfig.CASHPORT_API_ENDPOINT )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
    }

}
