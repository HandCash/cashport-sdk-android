package io.handcash.cashport;

import java.util.LinkedList;
import java.util.List;

import io.handcash.cashport.sdk.entity.PaymentRequest;
import io.handcash.cashport.sdk.entity.SignTransactionRequest;

public class SignTransactionRequestBuilder {

    private String apiId;
    private String handle;
    private String authToken;
    private String channelId;
    private List<PaymentRequest> payments;

    public static SignTransactionRequestBuilder useApiId(String apiId) {
        return new SignTransactionRequestBuilder( apiId );
    }

    public SignTransactionRequestBuilder(String apiId) {
        this.apiId = apiId;
    }

    public SignTransactionRequestBuilder withCredentials(String handle, String authToken, String channelId) {
        this.handle = handle;
        this.authToken = authToken;
        this.channelId = channelId;
        this.payments = new LinkedList<>();
        return this;
    }

    public SignTransactionRequestBuilder addPayment(PaymentRequest payment) {
        payments.add( payment );
        return this;
    }

    public SignTransactionRequest build() {
        SignTransactionRequest signTransactionRequest = new SignTransactionRequest();
        signTransactionRequest.setApiId( apiId );
        signTransactionRequest.setAuthToken( authToken );
        signTransactionRequest.setHandle( handle );
        signTransactionRequest.setChannelId( channelId );
        signTransactionRequest.setPayments( payments );
        return signTransactionRequest;
    }

}
