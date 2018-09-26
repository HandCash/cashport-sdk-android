package io.handcash.cashport.sdk.api;


import io.handcash.cashport.sdk.entity.SignTransactionRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ICashPortService {

    @POST( "signTransactionRequest" )
    Call<Void> sendSignRequest(@Body SignTransactionRequest signTransactionRequest);

}
